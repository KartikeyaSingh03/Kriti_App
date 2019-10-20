package com.example.kriti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourse extends AppCompatActivity {

    private EditText courseTitle;
    private EditText courseDescription;
    private String title,description,Club;
    private Button addVid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseTitle = (EditText) findViewById(R.id.course_title);
        courseDescription = (EditText) findViewById(R.id.course_description);
        addVid = (Button) findViewById((R.id.addVideo));


        addVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = courseTitle.getText().toString();
                description = courseDescription.getText().toString();
                final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                final String current = FirebaseAuth.getInstance().getUid().toString();
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String club = dataSnapshot.child("ClubDept").child(current).child("name").getValue().toString();
                        root.child(club).child("courses").child(title).child("OverView").child("OverView").setValue(description);
                        Intent i = new Intent(AddCourse.this,AddVideo.class);
                        i.putExtra("Title",title);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddCourse.this,FeedActivity.class));
    }
}
