package com.example.lab7managementsystem.Service;

import com.example.lab7managementsystem.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        courses.add(course);
    }

    public ArrayList<Course> getAllCourses(){
        return courses;
    }
    public boolean updateCourse(String id,Course course){
        for(int i=0; i<courses.size(); i++){
            if(courses.get(i).getCourseId().equalsIgnoreCase(id)){
                courses.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean removeCourse(String id){
        for(int i=0; i<courses.size();i++){
            if(courses.get(i).getCourseId().equalsIgnoreCase(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }
    public String setAvailability(String id){
        for(int i=0; i<courses.size(); i++){
            if(courses.get(i).getCourseId().equalsIgnoreCase(id)&&!courses.get(i).getIsAvailable()){
                courses.get(i).setIsAvailable(true);
                return "1"; //course is available now
            }else if(courses.get(i).getCourseId().equalsIgnoreCase(id)){
                return "2"; //course already available
            }
        }
        return "not found"; //course was not found
    }
    public ArrayList<Course> availableCourses(){
        ArrayList<Course> availableCourses = new ArrayList<>();
        for(int i=0; i<courses.size(); i++){
            if(courses.get(i).getIsAvailable()){
                availableCourses.add(courses.get(i));
            }
        }
        return availableCourses;
    }

    public ArrayList<Course> coursesWithFailStudents(String cid){
        return null;
    }

}
