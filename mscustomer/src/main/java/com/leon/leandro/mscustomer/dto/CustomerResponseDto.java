package com.leon.leandro.mscustomer.dto;

public record CustomerResponseDto(
        Long id,
        String name,
        String identification,
        Boolean active
) {}
