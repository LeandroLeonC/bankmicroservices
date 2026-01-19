package com.leon.leandro.msaccount.service;

import com.leon.leandro.msaccount.domain.entity.Account;
import com.leon.leandro.msaccount.dto.AccountReport;
import com.leon.leandro.msaccount.dto.AccountStatementReport;
import com.leon.leandro.msaccount.dto.CreateAccountRequest;
import com.leon.leandro.msaccount.dto.MovementReport;
import com.leon.leandro.msaccount.exception.BusinessException;
import com.leon.leandro.msaccount.repository.AccountRepository;
import com.leon.leandro.msaccount.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerClient customerClient;

    @Override
    public Account createAccount(CreateAccountRequest request) {
        if (request.initialBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Initial balance cannot be negative");
        }
        Account account = Account.create(request), reques;
        return accountRepository.save(account);
    }

    @Override
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = getActiveAccount(accountNumber);
        account.deposit(amount);
        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = getActiveAccount(accountNumber);
        account.withdraw(amount);
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BusinessException("Account not found"));
    }

    private Account getActiveAccount(String accountNumber) {
        Account account = getByAccountNumber(accountNumber);

        if (account.isBlocked() || account.isClosed()) {
            throw new BusinessException("Account is not active");
        }

        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountStatementReport generateReport(
            Long clienteId,
            LocalDateTime from,
            LocalDateTime to
    ) {

        if (from.isAfter(to)) {
            throw new BusinessException("Invalid date range");
        }

        // 1. validar cliente
        customerClient.validateCustomerExists(clienteId);

        // 2. obtener cuentas
        List<Account> accounts = accountRepository.findByCustomerId(clienteId);

        // 3. mapear reporte
        List<AccountReport> accountReports = accounts.stream()
                .map(account -> {

                    List<MovementReport> movements =
                            transactionRepository
                                    .findByAccountIdAndCreatedAtBetween(
                                            account.getId(), from, to
                                    )
                                    .stream()
                                    .map(tx -> new MovementReport(
                                            tx.getCreatedAt(),
                                            tx.getType().name(),
                                            tx.getAmount()
                                    ))
                                    .toList();

                    return new AccountReport(
                            account.getAccountNumber(),
                            account.getBalance(),
                            movements
                    );
                })
                .toList();

        return new AccountStatementReport(clienteId, accountReports);
    }
}
