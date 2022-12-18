package com.example.schoolmanagementsoftware.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    public enum Majors {
        CS,
        MATH,
        Engineer

    }

    @NotNull
    @Min(1)
    private Integer id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    private Integer age;

    @NotEmpty
    @Pattern(regexp = "(Math|CS|Engineering)")
    private String major;


}
