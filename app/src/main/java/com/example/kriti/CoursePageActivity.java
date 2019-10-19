package com.example.kriti;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CoursePageActivity extends AppCompatActivity {
    private static final String TAG = "CoursePageActivity";
    public static String pageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_page);

        Log.i(TAG,"this is the title : " + pageTitle);

        setTitle(pageTitle);

    }
}