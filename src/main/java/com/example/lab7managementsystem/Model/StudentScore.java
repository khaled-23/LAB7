package com.example.lab7managementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentScore {
    private String cId;
    @NotEmpty(message = "id should not be empty")
    @Size(min = 8,max = 8, message = "id should start with s followed by 7 digit")
    @Pattern(regexp = "^s[0-9]{7}$", message = "id should only contain 2 digit")
    private String sId;
    @NotNull(message = "garde should not be empty")
    @Min(value = 0, message = "minimum grade is 0")
    @Max(value = 100,message = "maximum grade is 100")
    @Pattern(regexp = ("^[0-9]+.[0-9]{2}$"),message = "grade should be entered with 2 decimal number '100.00'")
    private Double studentGrade;
}
