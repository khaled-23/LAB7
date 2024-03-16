package com.example.lab7managementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 2,max = 2, message = "id should contain two digit")
    @Pattern(regexp = "^[0-9]{2}$", message = "id should only contain 2 digit")
    private String id;
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+", message = "name should only contain letters")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(value = 26,message = "minimum age is 26")
    private Integer age;
    @NotEmpty(message = "phone number should not be empty")
    @Size(min = 10,max = 10, message = "phone number should be 10 digits")
    @Pattern(regexp = "^(05)[0-9]+$", message = "phone number should start with 05")
    private String phoneNumber;
}