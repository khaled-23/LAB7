package com.example.lab7managementsystem.Controller;

import com.example.lab7managementsystem.ApiResponse.ApiResponse;
import com.example.lab7managementsystem.Model.HomeWork;
import com.example.lab7managementsystem.Model.Teacher;
import com.example.lab7managementsystem.Service.HomeWorkService;
import com.example.lab7managementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final HomeWorkService homeWorkService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("teacher added"));
    }
    @GetMapping("/teachers")
    public ResponseEntity getAllTeachers(){
        ArrayList<Teacher> teachers = teacherService.getAllTeachers();
        if(teachers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no teachers"));
        }else return ResponseEntity.status(200).body(teachers);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = teacherService.updateTeacher(id, teacher);
        if(isUpdated){
            return ResponseEntity.status(200).body("teacher updated");
        }else return ResponseEntity.status(400).body("teacher not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeTeacher(@PathVariable String id){
        boolean isRemoved = teacherService.isRemoved(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("teacher removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("teacher not found"));
    }

    @PostMapping("/assign-score/{cid}/{sid}/{score}")
    public ResponseEntity assignScore(@PathVariable String cid,@PathVariable String sid, @PathVariable double score){
        String condition = teacherService.assignScore(cid,sid,score);
        return switch (condition) {
            case "0" -> ResponseEntity.status(200).body(new ApiResponse("score added"));
            case "-1" -> ResponseEntity.status(400).body(new ApiResponse("student not found"));
            default -> ResponseEntity.status(400).body(new ApiResponse("course not found"));
        };
    }
    @DeleteMapping("/remove-student/{cid}/{sid}")
    public ResponseEntity removeStudentFromCourse(@PathVariable String cid, @PathVariable String sid){
        String condition = teacherService.removeStudent(cid,sid);
        
        return switch(condition){
            case "-2" -> ResponseEntity.status(400).body(new ApiResponse("student doesn't have course "+cid));
            case "-1" -> ResponseEntity.status(400).body(new ApiResponse("student with id: "+sid+" doesn't exists"));
            case "0" -> ResponseEntity.status(200).body(new ApiResponse("student with id: "+sid+" was removed from course: "+ cid));
            default -> throw new IllegalStateException("Unexpected value: " + condition);
        };

    }

    @PostMapping("/add-home-work")
    public ResponseEntity addHomeWork(@RequestBody @Valid HomeWork homeWork,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        homeWorkService.addHomeWork(homeWork);
        return ResponseEntity.status(200).body("home work added");
    }
    



}
