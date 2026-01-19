package com.leon.leandro.msaccount.dto;

import java.math.BigDecimal;

public record AccountResponse(
        String accountNumber,
        BigDecimal balance,
        String status,
        Long customerId
) {}