package com.example.lab7managementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeWork {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 6,max = 6,message = "id should be 6 characters for ex cs-101")
    @Pattern(regexp = "^[a-zA-Z]{2}-[0-9]{3}$", message = "course id should look like cs-101, two letter followed by dash followed by 3 digit")
    private String cid;
    @NotEmpty(message = "title should not be empty")
    private String title;
    @NotEmpty(message = "body should not be empty")
    private String body;

}
