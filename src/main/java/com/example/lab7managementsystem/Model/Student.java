package com.example.lab7managementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 8,max = 8, message = "id should start with s followed by 7 digit")
    @Pattern(regexp = "^s[0-9]{7}$", message = "id should only contain 2 digit")
    private String id;
    @NotEmpty(message = "name should not be empty")
    @Pattern(regexp = "^[a-zA-Z]+", message = "name should only contain letters")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(value = 18,message = "minimum age is 18")
    private Integer age;
    @NotEmpty(message = "phone number should not be empty")
    @Size(min = 10,max = 10, message = "phone number should be 10 digits")
    @Pattern(regexp = "^(05)[0-9]+$", message = "phone number should start with 05")
    private String phoneNumber;
    @NotNull(message = "courses should not be empty")
    private ArrayList<Course> courses = new ArrayList<Course>();


    public void addCourse(Course course){
        this.courses.add(course);
    }
}
