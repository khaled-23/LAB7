package com.example.lab7managementsystem.Controller;

import com.example.lab7managementsystem.ApiResponse.ApiResponse;
import com.example.lab7managementsystem.Model.Course;
import com.example.lab7managementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("course added"));
    }
    @GetMapping("/courses")
    public ResponseEntity getAllCourses(){
        ArrayList<Course> courses = courseService.getAllCourses();
        if(courses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no courses"));
        }else return ResponseEntity.status(200).body(courses);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = courseService.updateCourse(id,course);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("course updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("course not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){
        boolean isRemoved = courseService.removeCourse(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("course removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
    }

    @PutMapping("/set-available/{id}")
    public ResponseEntity setAvailability(@PathVariable String id){
        String condition = courseService.setAvailability(id);

        return switch (condition) {
            case "1" -> ResponseEntity.status(200).body(new ApiResponse("course is now available"));
            case "2" -> ResponseEntity.status(400).body(new ApiResponse("course already available"));
            default -> ResponseEntity.status(400).body(new ApiResponse("course not found"));
        };
    }

    @GetMapping("/available-courses")
    public ResponseEntity availableCourses(){
        ArrayList<Course> availableCourses = courseService.availableCourses();
        if(availableCourses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no available courses"));
        }else return ResponseEntity.status(200).body(availableCourses);
    }

}


