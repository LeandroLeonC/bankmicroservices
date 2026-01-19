package com.leon.leandro.msaccount.controller;

import com.leon.leandro.msaccount.dto.AccountStatementReport;
import com.leon.leandro.msaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final AccountService reportService;

    @GetMapping
    public AccountStatementReport getAccountStatement(
            @RequestParam Long clienteId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ) {

        return reportService.generateReport(
                clienteId,
                from.atStartOfDay(),
                to.atTime(23, 59, 59)
        );
    }
}
