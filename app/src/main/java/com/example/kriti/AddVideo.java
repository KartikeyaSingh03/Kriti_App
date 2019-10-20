package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddVideo extends AppCompatActivity {

     private TextView ctitle, cdes;
     private EditText vidTitle, vidURL;
     private String videoTitle, videoURL;
     private Button vidAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        ctitle = findViewById(R.id.cTitle);
        cdes = findViewById(R.id.cDes);
        vidTitle = findViewById(R.id.video_title);
        vidURL = findViewById(R.id.video_url);
        vidAdd = findViewById(R.id.add_Video);

        videoTitle = vidTitle.getText().toString();
        videoURL = vidURL.getText().toString();

        vidAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddVideo.this, FeedActivity.class));
                Toast.makeText(AddVideo.this,"Video Added",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddVideo.this,FeedActivity.class));
    }
}
