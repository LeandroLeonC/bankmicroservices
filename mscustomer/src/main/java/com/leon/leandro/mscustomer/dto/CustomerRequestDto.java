package com.leon.leandro.mscustomer.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDto(
        @NotBlank String name,
        @NotBlank String gender,
        @NotNull Integer age,
        @NotBlank String identification,
        @NotBlank String address,
        @NotBlank String phone,
        @NotBlank String password,
        @NotNull Boolean active
) {}