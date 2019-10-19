package com.example.kriti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public  class SignInActivity extends AppCompatActivity {
    EditText Email,Pass;
    String email,pass;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button signIn = findViewById(R.id.sign_in_btn);
        Button register = findViewById(R.id.registerButton);
        Button clubreg =findViewById(R.id.ClubRegButton);
        Email =findViewById(R.id.email);
        Pass =findViewById(R.id.password);
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseDatabase =FirebaseDatabase.getInstance();
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPost=new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(intentPost);

            }

        });

        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), FeedActivity.class));
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString().trim();
                pass=Pass.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(SignInActivity.this,"Please enter an email ID",Toast.LENGTH_LONG).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(SignInActivity.this,"Please enter a password",Toast.LENGTH_LONG).show();
                }
                else{
                    final ProgressDialog progressDialog =new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Signing in...");
                    progressDialog.show();
                    //logging in the user
                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if the task is successful
                                    if(task.isSuccessful()){
                                        //start the profile activity
                                        String currentuser = firebaseAuth.getCurrentUser().getUid();
                                        DatabaseReference root = firebaseDatabase.getReference();
                                        root.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                if(currentuser.isEmpty()){
                                                    Toast.makeText(SignInActivity.this,"Error",Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    if (dataSnapshot.child("ClubDept").child(currentuser).exists()) {
                                                        Toast.makeText(SignInActivity.this, "Sign in as a Club/Dept", Toast.LENGTH_LONG).show();
                                                        firebaseAuth.signOut();
                                                    } else {
                                                        finish();
                                                        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(SignInActivity.this,"Incorrect credentials",Toast.LENGTH_LONG).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
            }
        });

        clubreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=Email.getText().toString().trim();
                pass=Pass.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(SignInActivity.this,"Please enter an email ID",Toast.LENGTH_LONG).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(SignInActivity.this,"Please enter a password",Toast.LENGTH_LONG).show();
                }
                else{
                    final ProgressDialog progressDialog =new ProgressDialog(SignInActivity.this);
                    progressDialog.setMessage("Signing in...");
                    progressDialog.show();
                    //logging in the user
                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    //if the task is successful
                                    if(task.isSuccessful()){
                                        //start the profile activity
                                        String currentuser = firebaseAuth.getCurrentUser().getUid();
                                        DatabaseReference root = firebaseDatabase.getReference();
                                        root.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                if(currentuser.isEmpty()){
                                                    Toast.makeText(SignInActivity.this,"Error",Toast.LENGTH_LONG).show();
                                                }
                                                else {
                                                    if (dataSnapshot.child("Users").child(currentuser).exists()) {
                                                        Toast.makeText(SignInActivity.this, "Sign in as a User", Toast.LENGTH_LONG).show();
                                                        firebaseAuth.signOut();
                                                    } else {
                                                        finish();
                                                        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                    else{
                                        Toast.makeText(SignInActivity.this,"Incorrect credentials",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

    }




}
