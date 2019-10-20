package com.example.kriti;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BookPageActivity extends AppCompatActivity {
    private static final String TAG = "BookPageActivity";
    public static String pageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_page);

        Log.i(TAG,"this is the book title : " + pageTitle);

        setTitle(pageTitle);

    }
}