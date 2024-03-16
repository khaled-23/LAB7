package com.example.lab7managementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 6,max = 6,message = "id should be 6 characters for ex cs-101")
    @Pattern(regexp = "^[a-zA-Z]{2}-[0-9]{3}$", message = "course id should look like cs-101, two letter followed by dash followed by 3 digit")
    private String courseId;
    @NotEmpty(message = "course name should not be empty")
    private String name;
    @NotNull(message = "course credit hour should not be empty")
    @Min(value = 1, message = "minimum credit hours is 1")
    @Max(value = 4, message = "maximum credit hours is 4")
    private Integer creditHour;
    @AssertFalse(message = "availability should be false")
    private Boolean isAvailable;
//    @NotNull(message = "garde should not be empty")
//    @Min(value = 0, message = "minimum grade is 0")
//    @Max(value = 100,message = "maximum grade is 100")
//    @Pattern(regexp = ("^[0-9]+.[0-9]{2}$"),message = "grade should be entered with 2 decimal number '100.00'")
//    private Double grade;

    public Course(String courseId){
        this.courseId = courseId;
    }

}
