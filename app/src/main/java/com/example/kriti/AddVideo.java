package com.example.kriti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddVideo extends AppCompatActivity {

     private TextView ctitle, cdes;
     private EditText vidTitle, vidURL;
     private String videoTitle, videoURL,CourseName;
     private Button vidAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        Bundle bundle = getIntent().getExtras();
        ctitle = findViewById(R.id.cTitle);
        if(bundle!=null)
            CourseName = bundle.getString("Title") ;
        ctitle.setText(CourseName);
        cdes = findViewById(R.id.cDes);
        vidTitle = findViewById(R.id.video_title);
        vidURL = findViewById(R.id.video_url);
        vidAdd = findViewById(R.id.add_Video);

        /*DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child()
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        vidAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoTitle = vidTitle.getText().toString();
                videoURL = vidURL.getText().toString();
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
