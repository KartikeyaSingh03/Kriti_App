package com.example.kriti;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {
    private static final String TAG = "CoursesActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        final ArrayList<Item> items = new ArrayList<>();

        for(int i=0; i<7; ++i) {


            items.add(new Item("Course Title" + i, "Description" + i));
        }




        ItemAdapter adapter = new ItemAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = items.get(i);
                Log.i(TAG,"this is" + item.getHeading());
                Intent intentPost=new Intent(CoursesActivity.this,CoursePageActivity.class);

                CoursePageActivity.pageTitle = item.getHeading();
                startActivity(intentPost);


            }
        });
    }
}
