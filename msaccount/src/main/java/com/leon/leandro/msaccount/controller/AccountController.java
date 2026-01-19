package com.leon.leandro.msaccount.controller;

import com.leon.leandro.msaccount.domain.entity.Account;
import com.leon.leandro.msaccount.dto.AccountResponse;
import com.leon.leandro.msaccount.dto.AmountRequest;
import com.leon.leandro.msaccount.dto.CreateAccountRequest;
import com.leon.leandro.msaccount.mapper.AccountMapper;
import com.leon.leandro.msaccount.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid  @RequestBody CreateAccountRequest request
    ) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountMapper.toResponse(account));
    }

    @PostMapping("/{accountNumber}/deposit")
    public AccountResponse deposit(
            @PathVariable String accountNumber,
            @Valid @RequestBody AmountRequest request
    ) {
        Account account = accountService.deposit(accountNumber, request.amount());
        return AccountMapper.toResponse(account);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public AccountResponse withdraw(
            @PathVariable String accountNumber,
            @Valid  @RequestBody AmountRequest request
    ) {
        Account account = accountService.withdraw(accountNumber, request.amount());
        return AccountMapper.toResponse(account);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccount(
            @PathVariable String accountNumber
    ) {
        return AccountMapper.toResponse(
                accountService.getByAccountNumber(accountNumber)
        );
    }
}
