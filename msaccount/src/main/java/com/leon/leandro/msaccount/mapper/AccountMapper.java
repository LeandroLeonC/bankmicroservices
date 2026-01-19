package com.leon.leandro.msaccount.mapper;

import com.leon.leandro.msaccount.domain.entity.Account;
import com.leon.leandro.msaccount.dto.AccountResponse;

public class AccountMapper {
    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus().name(),
                account.getCustomerId()
        );
    }
}
