package com.example.fragmenttestapp2.data;

import java.util.ArrayList;

public class CourseData {
    private String[] courseNames = {
            "First course",
            "Second course",
            "Third course",
            "Fourth course",
            "Fifth course",
            "Sixth course",
            "Seventh course"
    };

    public ArrayList<Course> courseList() {
        ArrayList<Course> list = new ArrayList<>();
        for(int i = 0; i < courseNames.length; i++) {
            Course course = new Course(
                    courseNames[i],
                    courseNames[i].replace(" ", "").toLowerCase()
            );
            list.add(course);
        }

        return list;
    }
}
