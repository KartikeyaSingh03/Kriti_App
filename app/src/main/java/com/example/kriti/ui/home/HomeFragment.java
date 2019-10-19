package com.example.kriti.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kriti.CoursesActivity;
import com.example.kriti.MainActivity;
import com.example.kriti.R;
import com.example.kriti.ReadingMaterialActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private  Button courses;

    private Button material;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        courses = (Button) root.findViewById(R.id.coursesButton) ;
        courses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPost=new Intent(getContext(), CoursesActivity.class);
                startActivity(intentPost);

            }

        });
         material= (Button) root.findViewById(R.id.readingMaterial) ;
        material.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentPost=new Intent(getContext(), ReadingMaterialActivity.class);
                startActivity(intentPost);

            }

        });

        return root;
    }
}