package com.example.lab7managementsystem.Service;

import com.example.lab7managementsystem.Model.HomeWork;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HomeWorkService {
    ArrayList<HomeWork> homeWorks = new ArrayList<>();


    public void addHomeWork(HomeWork homeWork){
        homeWorks.add(homeWork);
    }
}
