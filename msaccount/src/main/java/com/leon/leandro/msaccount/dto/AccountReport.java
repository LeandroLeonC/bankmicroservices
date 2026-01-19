package com.leon.leandro.msaccount.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountReport(
        String numeroCuenta,
        BigDecimal saldoActual,
        List<MovementReport> movimientos
) {}
