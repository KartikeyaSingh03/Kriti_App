package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button courses = (Button) findViewById(R.id.coursesButton) ;
        courses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPost=new Intent(MainActivity.this,CoursesActivity.class);
                startActivity(intentPost);

            }

        });

        Button material = (Button) findViewById(R.id.readingMaterial) ;
        material.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPost=new Intent(MainActivity.this,ReadingMaterialActivity.class);
                startActivity(intentPost);

            }

        });
    }


}
