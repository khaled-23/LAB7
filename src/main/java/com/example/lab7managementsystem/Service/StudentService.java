package com.example.lab7managementsystem.Service;

import com.example.lab7managementsystem.Model.Course;
import com.example.lab7managementsystem.Model.Student;
import com.example.lab7managementsystem.Model.StudentScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();
    private final CourseService courseService;
    private final StudentScoreService studentScoreService;

    public void addStudent(Student student){
        students.add(student);
    }

    public ArrayList<Student> getAllStudent(){
        return students;
    }
    public boolean updateStudent(String id, Student student){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.set(i,student);
                return true;
            }
        }
        return false;
    }
    public boolean removeStudent(String id){
        for(int i =0; i<students.size();i++){
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public String addCourse(String sid ,String cid){
        ArrayList<Course> courses = courseService.courses;

        //check for student
        int studentIndex = getStudentIndex(sid);
        if(studentIndex==-1){
            return "0"; //student not found
        }
        //check for course
        for(int i=0; i<courseService.courses.size(); i++){
            if(courses.get(i).getCourseId().equalsIgnoreCase(cid) && courses.get(i).getIsAvailable()){
                students.get(studentIndex).addCourse(courses.get(i));
                return "1"; //course added
            }else if(courses.get(i).getCourseId().equalsIgnoreCase(cid) && !courses.get(i).getIsAvailable()){
                return "2"; //course not available
            }
        }
        return "not found"; //course not found
    }

    public ArrayList<Course> getStudentCourses(String sid){
        ArrayList<Course> studentCourses = new ArrayList<>();
        for(int i=0; i<students.size();i++){
            if(students.get(i).getId().equalsIgnoreCase(sid)){
                studentCourses = students.get(i).getCourses();
            }
        }
        return studentCourses;
    }

    public int getTotalHours(String sid){
        int studentIndex = getStudentIndex(sid);
        if(studentIndex == -1){
            return -1;//student not found
        }
        int totalHours = 0;
        for(int i=0; i<students.get(studentIndex).getCourses().size(); i++){
            totalHours += students.get(studentIndex).getCourses().get(i).getCreditHour();
        }
        return totalHours;
    }

    public double getScore(String cid, String sid){
        ArrayList<StudentScore> studentsScores = studentScoreService.studentScores;
        int studentIndex =-1;
        for(int i=0; i<students.size();i++){
            if(students.get(i).getId().equalsIgnoreCase(sid)){
                studentIndex=i;
            }
        }
        if(studentIndex==-1){
            return -1;
        }
        for(int i=0;i<studentsScores.size();i++){
            if(students.get(studentIndex).getCourses().get(i).getCourseId().equalsIgnoreCase(cid)){
                return studentScoreService.getStudentScore(cid,sid);
            }
        }
        return -2; //course not found
    }

    public int getStudentIndex(String sid){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getId().equalsIgnoreCase(sid)){
                return i;
            }
        }
        return -1;
    }


}
