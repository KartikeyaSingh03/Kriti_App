package com.example.kriti;

import com.google.firebase.database.FirebaseDatabase;

public class MyFireBaseApp extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
