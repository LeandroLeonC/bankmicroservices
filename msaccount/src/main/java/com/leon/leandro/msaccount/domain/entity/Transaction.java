package com.leon.leandro.msaccount.domain.entity;

import com.leon.leandro.msaccount.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public static Transaction deposit(Account account, BigDecimal amount) {
        return Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Transaction withdrawal(Account account, BigDecimal amount) {
        return Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.WITHDRAWAL)
                .createdAt(LocalDateTime.now())
                .build();
    }

}

