package com.example.lab7managementsystem.Controller;

import com.example.lab7managementsystem.ApiResponse.ApiResponse;
import com.example.lab7managementsystem.Model.Course;
import com.example.lab7managementsystem.Model.Student;
import com.example.lab7managementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
private final StudentService studentService;


    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added"));
    }
    @GetMapping("/students")
    public ResponseEntity getAllStudent(){
        ArrayList<Student> students = studentService.getAllStudent();
        if(students.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there are no students"));
        }else return ResponseEntity.status(200).body(students);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = studentService.updateStudent(id,student);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("student updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("student was not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        boolean isRemoved = studentService.removeStudent(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("student removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("student was not found"));
    }

    @PostMapping("/add-course/{sid}/{cid}")
    public ResponseEntity addCourse(@PathVariable String sid,@PathVariable String cid){
        String condition = studentService.addCourse(sid,cid);
        return switch (condition) {
            case "0" -> ResponseEntity.status(400).body(new ApiResponse("student was not found"));
            case "1" -> ResponseEntity.status(200).body(new ApiResponse("course added"));
            case "2" -> ResponseEntity.status(400).body(new ApiResponse("course is not available"));
            default -> ResponseEntity.status(400).body(new ApiResponse("course not found"));
        };
    }

    //get students courses
    @GetMapping("/courses/{sid}")
    public ResponseEntity getStudentCourses(@PathVariable String sid){
        int studentIndex = studentService.getStudentIndex(sid);
        if(studentIndex==-1){
            return ResponseEntity.status(400).body(new ApiResponse("student not found"));
        }
        ArrayList<Course> studentCourses = studentService.getStudentCourses(sid);
        if(studentCourses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("student with id: "+sid+" doesn't have courses"));
        }else return ResponseEntity.status(200).body(studentCourses);
    }

    @GetMapping("/total-hours/{sid}")
    public ResponseEntity getTotalCreditHours(@PathVariable String sid){
        int totalHours = studentService.getTotalHours(sid);
        if(totalHours==-1){
            return ResponseEntity.status(400).body(new ApiResponse("student was not found"));
        }else return ResponseEntity.status(200).body(new ApiResponse("total hours for student: "+sid+" is "+totalHours));
    }

    @GetMapping("/score/{cid}/{sid}")//get total score for given course
    public ResponseEntity getScore(@PathVariable String cid, @PathVariable String sid){

        double score = studentService.getScore(cid,sid);

        return switch ((int) score){
            case -1 -> ResponseEntity.status(400).body(new ApiResponse("student not found"));
            case -2 -> ResponseEntity.status(400).body(new ApiResponse("course not found"));
            default -> ResponseEntity.status(200).body(new ApiResponse("score is: "+score));
        };
    }

}
