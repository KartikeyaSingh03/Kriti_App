package com.example.kriti;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class CoursePageActivity extends AppCompatActivity {
    private static final String TAG = "CoursePageActivity";
    public static String pageTitle;


    private ImageButton playImage;
    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_course_page);

        Log.i(TAG,"this is the title : " + pageTitle);

        setTitle(pageTitle);
        progressBar = (ProgressBar) findViewById(R.id.progrss);
        vv = (VideoView) findViewById(R.id.vv);
        playImage= (ImageButton) findViewById(R.id.play);

        playImage.setVisibility(View.VISIBLE);

        mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        String uriPath = "https://firebasestorage.googleapis.com/v0/b/kritiapp-2f73b.appspot.com/o/game%20-%20Copy.mp4?alt=media&token=6e3747b8-0107-42aa-84e8-65298baf4d85"; //update package name
        uri = Uri.parse(uriPath);


        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                playImage.setVisibility(View.GONE);
                vv.setVisibility(View.VISIBLE);
                vv.setMediaController(mediacontroller);
                vv.setVideoURI(uri);
                vv.requestFocus();
                vv.start();
            }
        });



        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    }
