package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse extends AppCompatActivity {

    private EditText courseTitle;
    private EditText courseDescription;
    private String title,description;
    private Button addVid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseTitle = (EditText) findViewById(R.id.course_title);
        courseDescription = (EditText) findViewById(R.id.course_description);
        addVid = (Button) findViewById((R.id.addVideo));

        title = courseTitle.getText().toString();
        description = courseDescription.getText().toString();

        addVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCourse.this,AddVideo.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddCourse.this,FeedActivity.class));
    }
}
