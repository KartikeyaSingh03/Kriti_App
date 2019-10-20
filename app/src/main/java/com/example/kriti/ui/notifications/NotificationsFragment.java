package com.example.kriti.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kriti.BookPageActivity;
import com.example.kriti.CoursePageActivity;
import com.example.kriti.CoursesAndBooks;
import com.example.kriti.Item;
import com.example.kriti.ItemAdapter;
import com.example.kriti.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    FirebaseDatabase database;
    private String deptOrClub;
    String courseName,courseOver;
    ListView listView;
    ItemAdapter adapter;
    ArrayList<Item> items=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.activity_courses, container, false);


        deptOrClub=CoursesAndBooks.pageTitle;
        database = FirebaseDatabase.getInstance();
        DatabaseReference rootDatabase =database.getReference();
        rootDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(deptOrClub).exists()){
                    for(DataSnapshot snapshot : dataSnapshot.child(deptOrClub).child("courses").getChildren()){

                        if(snapshot.exists()) {
                            courseName = snapshot.getKey();
                        }
                        if(snapshot.child("OverView").child("OverView").exists()) {
                            courseOver = snapshot.child("OverView").child("OverView").getValue().toString();
                        }
                        items.add(new Item(courseName,courseOver));

                    }
                    adapter = new ItemAdapter(getActivity(), items);
                    listView = (ListView) root.findViewById(R.id.list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                            Item item = items.get(i);
                            Intent intentPost=new Intent(getActivity(), CoursePageActivity.class);

                            CoursePageActivity.pageTitle = item.getHeading();
                            startActivity(intentPost);


                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}