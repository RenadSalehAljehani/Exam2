package com.example.exam2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "ID can't be null.")
    private String ID;
    @NotEmpty(message = "Name can't be null.")
    private String name;
    @NotNull(message = "Age can't be null.")
    private int age;
    @NotNull(message = "Balance can't be null.")
    @PositiveOrZero(message = "Balance must be a positive number or zero.")
    private double balance;
    @NotEmpty(message = "Role can't be null.")
    @Pattern(regexp = "^(?i)(customer|libraian)$" ,message = "Role must be only customer or libraian.")
    private String role;
}