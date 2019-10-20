package com.example.kriti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddVideo extends AppCompatActivity {

     private TextView ctitle, cdes;
     private EditText vidTitle, vidURL;
     private String videoTitle, videoURL,CourseName,ClubName;
     private Button vidAdd;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storageReference = FirebaseStorage.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        Bundle bundle = getIntent().getExtras();
        ctitle = findViewById(R.id.cTitle);
        if(bundle!=null)
            ClubName = bundle.getString("Title") ;
        ctitle.setText(ClubName);
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
                CourseName = cdes.getText().toString();
                final DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(ClubName).child("courses").child(CourseName).exists()) {
                            if(!videoTitle.isEmpty()) {
                                StorageReference reference = storageReference.child("videos");
                                final ProgressDialog progressDialog = new ProgressDialog(AddVideo.this);
                                progressDialog.setTitle("Uploading...");
                                progressDialog.show();
                                StorageMetadata metadata = new StorageMetadata.Builder()
                                        .setContentType("video/mp4")
                                        .build();
                                reference.putFile(Uri.parse(videoURL),metadata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                                        while (!uri.isComplete()) ;
                                        Uri url = uri.getResult();
                                        root.child(ClubName).child("courses").child(CourseName).child("urls").child(videoTitle).child("url").setValue(url.toString());
                                        progressDialog.dismiss();
                                        Toast.makeText(AddVideo.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                                        finish();
                                        Intent i = new Intent(AddVideo.this, FeedActivity.class);
                                        startActivity(i);
                                        startActivity(new Intent(AddVideo.this, FeedActivity.class));
                                        Toast.makeText(AddVideo.this,"Video Added",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddVideo.this,"Failed",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(AddVideo.this,"Error",Toast.LENGTH_LONG).show();
                            }

                           // dataSnapshot.child(ClubName).child("courses").child(CourseName).child("urls").child(videoTitle).child("url").setValue(videoURL);
                        }
                        else{
                            Toast.makeText(AddVideo.this,"Course Doesn't Exists",Toast.LENGTH_LONG).show();
                        }
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
        startActivity(new Intent(AddVideo.this,FeedActivity.class));
    }
}
