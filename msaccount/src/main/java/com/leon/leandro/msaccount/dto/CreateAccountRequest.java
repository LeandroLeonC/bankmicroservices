package com.leon.leandro.msaccount.dto;

import com.leon.leandro.msaccount.domain.enums.AccountType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotNull(message = "customerId is required")
        Long customerId,
        AccountType type,
        BigDecimal initialBalance
) {}