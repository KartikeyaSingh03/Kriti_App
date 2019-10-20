package com.example.kriti;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoursePageActivity extends AppCompatActivity {
    private static final String TAG = "CoursePageActivity";
    public static String pageTitle;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);



        final ArrayList<Item> items = new ArrayList<>();



        items.add(new Item("CSE", "https://firebasestorage.googleapis.com/v0/b/kritiapp-2f73b.appspot.com/o/game%20-%20Copy.mp4?alt=media&token=6e3747b8-0107-42aa-84e8-65298baf4d85"));
        items.add(new Item("FikSHun", "https://firebasestorage.googleapis.com/v0/b/kritiapp-2f73b.appspot.com/o/videos?alt=media&token=cc0b7541-d87b-41f6-a9e2-ea85a72e1a97"));

        ItemAdapter adapter = new ItemAdapter(CoursePageActivity.this, items);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

//        Log.i(TAG,"this is the title : " + pageTitle);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                Item item = items.get(i);
                VideoPlayActivity.uriPath=item.getDescription();
                startActivity(new Intent(CoursePageActivity.this,VideoPlayActivity.class));




            }
        });

        setTitle(pageTitle);

    }

    }
