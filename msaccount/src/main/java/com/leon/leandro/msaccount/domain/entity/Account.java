package com.leon.leandro.msaccount.domain.entity;

import com.leon.leandro.msaccount.domain.enums.AccountStatus;
import com.leon.leandro.msaccount.domain.enums.AccountType;
import com.leon.leandro.msaccount.dto.CreateAccountRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    private List<Transaction> transactions = new ArrayList<>();

    private static String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis();
    }

    public static Account create(CreateAccountRequest request) {
        Account account = new Account();
        account.accountNumber = generateAccountNumber();
        account.balance = request.initialBalance();
        account.status = AccountStatus.ACTIVE;
        account.customerId = request.customerId();
        account.type = request.type();

        return account;
    }


    public void deposit(BigDecimal amount) {
        validateAmount(amount);

        this.balance = this.balance.add(amount);
        this.transactions.add(Transaction.deposit(this, amount));
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);

        if (this.balance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        this.balance = this.balance.subtract(amount);
        this.transactions.add(Transaction.withdrawal(this, amount));
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid amount");
        }
    }
    public boolean isBlocked() {
        return this.status == AccountStatus.BLOCKED;
    }

    public boolean isClosed() {
        return this.status == AccountStatus.CLOSED;
    }

}
