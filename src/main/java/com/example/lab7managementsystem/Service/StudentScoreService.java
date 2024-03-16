package com.example.lab7managementsystem.Service;

import com.example.lab7managementsystem.Model.StudentScore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentScoreService {
    ArrayList<StudentScore> studentScores = new ArrayList<>();


    public void addStudentScore(StudentScore studentScore){
        studentScores.add(studentScore);
    }

    public double getStudentScore(String cId, String sId){
        for(int i=0;i<studentScores.size();i++){
           if(studentScores.get(i).getCId().equalsIgnoreCase(cId)&&studentScores.get(i).getSId().equalsIgnoreCase(sId)){
               return studentScores.get(i).getStudentGrade();
           }
        }
        return 0;
    }

}
