package com.example.kriti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {
    TextView Name,Roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Name = findViewById(R.id.nameField);
        Roll = findViewById(R.id.RollField);
        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(currentuser).exists()){
                    String name = dataSnapshot.child("Users").child(currentuser).child("name").getValue().toString();
                    String roll = dataSnapshot.child("Users").child(currentuser).child("roll").getValue().toString();
                    if(!name.isEmpty())
                        Name.setText(name);
                    if(!roll.isEmpty())
                        Roll.setText(roll);
                }
                else if(dataSnapshot.child("ClubDept").child(currentuser).exists()){
                    String name = dataSnapshot.child("ClubDept").child(currentuser).child("name").getValue().toString();
                    if(!name.isEmpty())
                        Name.setText(name);
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
