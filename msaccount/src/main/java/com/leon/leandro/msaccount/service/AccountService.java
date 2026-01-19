package com.leon.leandro.msaccount.service;

import com.leon.leandro.msaccount.domain.entity.Account;
import com.leon.leandro.msaccount.dto.AccountStatementReport;
import com.leon.leandro.msaccount.dto.CreateAccountRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountService {

    Account createAccount(CreateAccountRequest request);

    Account deposit(String accountNumber, BigDecimal amount);

    Account withdraw(String accountNumber, BigDecimal amount);

    Account getByAccountNumber(String accountNumber);
     AccountStatementReport generateReport(
            Long clienteId,
            LocalDateTime from,
            LocalDateTime to
    );
}
