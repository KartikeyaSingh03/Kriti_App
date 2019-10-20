package com.example.kriti;

import java.util.ArrayList;

public class Course {
    String courseName;
    String courseOverview;
    ArrayList<String> urls;

    public Course(String courseName, String courseOverview, ArrayList<String> urls) {
        this.courseName = courseName;
        this.courseOverview = courseOverview;
        this.urls = urls;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseOverview() {
        return courseOverview;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }
}
