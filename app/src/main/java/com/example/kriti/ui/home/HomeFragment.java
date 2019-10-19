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
import androidx.viewpager.widget.ViewPager;

import com.example.kriti.CoursesActivity;
import com.example.kriti.MainActivity;
import com.example.kriti.R;
import com.example.kriti.ReadingMaterialActivity;
import com.example.kriti.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private  Button courses;

    private Button material;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager viewPager = (ViewPager) root.findViewById(R.id.viewpager);

         SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getActivity(), getFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);

     //   viewPager.setOffscreenPageLimit(2);


        tabLayout.setupWithViewPager(viewPager);





        return root;
    }
}