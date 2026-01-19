package com.leon.leandro.mscustomer.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    protected String name;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    protected String gender;

    @NotNull
    @Column(nullable = false)
    protected Integer age;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    protected String identification;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    protected String address;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    protected String phone;
}