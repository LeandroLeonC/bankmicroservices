package com.leon.leandro.msaccount.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementReport(
        LocalDateTime fecha,
        String tipo,
        BigDecimal valor
) {}