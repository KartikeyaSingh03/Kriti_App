package com.example.kriti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity  {
    private static final String TAG = "RegisterActivity";
    String department,name,email,pass,confpass,roll;
    EditText NameTF,EmailTF,PassTF,ConfirmTF,RollTF;
    Button Reg_Btn;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NameTF= findViewById(R.id.NameF);
        EmailTF= findViewById(R.id.EmailF);
        PassTF= findViewById(R.id.PassF);
        RollTF= findViewById(R.id.RollF);
        ConfirmTF= findViewById(R.id.confF);
        Reg_Btn=findViewById(R.id.reg_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Reg_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = NameTF.getText().toString().trim();
                roll = RollTF.getText().toString().trim();
                email = EmailTF.getText().toString().trim();
                pass = PassTF.getText().toString().trim();
                confpass = ConfirmTF.getText().toString().trim();
                if(!isValidName(name)||name.isEmpty()){
                    Toast.makeText(RegisterActivity.this,name,Toast.LENGTH_LONG).show();
                }
                else if(!isValidRoll(roll)||roll.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Roll no is Invlaid",Toast.LENGTH_LONG).show();
                }
                else if(!isValidEmail(email)||email.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Enter a valid Email",Toast.LENGTH_LONG).show();
                }
                else if(!passStrength(pass)){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters long, must contain a letter[a-z,A-Z], and a number[0-9]", Toast.LENGTH_LONG).show();
                }
                else if(!pass.equals(confpass)){
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_LONG).show();
                }
                else{
                    final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("Registering Please Wait...");
                    progressDialog.show();
                    //creating a new user
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //checking if success
                                    if(task.isSuccessful()){
                                        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        DatabaseReference root = database.getReference();
                                        Students s = new Students(name,roll);
                                        root.child("Users").child(currentuser).setValue(s);
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }else{
                                        //display some message here
                                        Toast.makeText(RegisterActivity.this,"Webmail ID is already registered",Toast.LENGTH_LONG).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });

                }

            }
        });
    }

    public static Boolean passStrength(String password){
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                }
                else if (Character.isDigit(x)) {
                    hasDigit = true;
                }
            }
            if(hasLetter && hasDigit){
                return true;
            }
        }
        return false;
    }

    public boolean isValidRoll(String s){
        if(s.length()!=9)
            return false;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<'0'||s.charAt(i)>'9')
                return false;
        }
        return true;
    }

    public boolean isValidName(String s){

        for(int i=0;i<s.length();i++){
            char c =s.charAt(i);
            if(Character.isLetter(c)==false&&c!=' ')
                return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
