package com.example.schoolmanagementsoftware.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotNull
    private Integer id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    private Float salary;
}
