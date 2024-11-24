package com.example.exam2.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "Id can't be null.")
    private String ID;
    @NotEmpty(message = "Name can't be null.")
    private String name;
    @NotNull(message = "Number of pages can't be null.")
    private int number_of_pages;
    @NotNull(message = "Price can't be null.")
    private double price;
    @NotEmpty(message = "Category can't be null.")
    @Pattern(regexp = "^(?i)(novel|academic)$" , message = "Category must be only novel or academic.")
    private String category;
    @AssertFalse(message = "isAvailable must be false.")
    private boolean isAvailable;
}