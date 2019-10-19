package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Courses(View view){
        Intent intentPost=new Intent(MainActivity.this,CoursesActivity.class);
        startActivity(intentPost);
    }

    public void readMaterial(View view){
        Intent intentPost=new Intent(MainActivity.this,ReadingMaterialActivity.class);
        startActivity(intentPost);
    }
}
