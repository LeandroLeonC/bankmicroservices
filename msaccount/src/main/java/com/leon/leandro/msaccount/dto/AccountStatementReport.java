package com.leon.leandro.msaccount.dto;

import java.util.List;

public record AccountStatementReport(
        Long clienteId,
        List<AccountReport> cuentas
) {}