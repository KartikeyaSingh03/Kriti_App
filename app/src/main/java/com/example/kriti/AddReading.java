package com.example.kriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddReading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reading);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddReading.this,FeedActivity.class));
    }
}
