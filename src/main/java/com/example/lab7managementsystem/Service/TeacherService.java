package com.example.lab7managementsystem.Service;

import com.example.lab7managementsystem.Model.Course;
import com.example.lab7managementsystem.Model.Student;
import com.example.lab7managementsystem.Model.StudentScore;
import com.example.lab7managementsystem.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();
    private final CourseService courseService;
    private final StudentService studentService;
    private final StudentScoreService studentScoreService;
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public ArrayList<Teacher> getAllTeachers(){
        return teachers;
    }
    public boolean updateTeacher(String id, Teacher teacher){
        for(int i=0; i<teachers.size(); i++){
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;
    }

    public boolean isRemoved(String id){
        for(int i=0; i<teachers.size(); i++){
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public String assignScore(String cid, String sid, double score){
        ArrayList<Student> students = studentService.students;
        ArrayList<Course> courses = courseService.courses;
        int studentIndex = -1;
        for(int i=0; i<students.size();i++){
            if(students.get(i).getId().equalsIgnoreCase(sid)){
                studentIndex = i;
                break;
            }
        }
        if(studentIndex==-1){
            return "-1";
        }
        for(int i=0;i<courses.size();i++){
            if(courses.get(i).getCourseId().equalsIgnoreCase(cid)){
                studentScoreService.addStudentScore(new StudentScore(cid,sid,score));
                return "0"; //score added
            }
        }
        return "1"; //course not found
    }

    public String removeStudent(String cid, String sid){
        int studentIndex = -1;
        for(int i=0; i<studentService.students.size();i++){
            if(studentService.students.get(i).getId().equalsIgnoreCase(sid)){
                studentIndex=i;
            }
        }
        if(studentIndex==-1){
            return "-1";//student not found
        }
        for(int i=0; i<studentService.students.get(studentIndex).getCourses().size();i++){
            if(studentService.students.get(studentIndex).getCourses().get(i).getCourseId().equalsIgnoreCase(cid)){
                studentService.students.get(studentIndex).getCourses().remove(i);
                return "0";// student removed from course
            }
        }
        return "-2"; //course not found
    }


}
