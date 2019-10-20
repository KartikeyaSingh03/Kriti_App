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
import com.example.kriti.Item;
import com.example.kriti.ItemAdapter;
import com.example.kriti.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.activity_courses, container, false);
        final ArrayList<Item> items = new ArrayList<>();

        for(int i = 0; i < 9 ; ++i){

            items.add(new Item("Book Title " +i,"Book Description " ));
        }
        ItemAdapter adapter = new ItemAdapter(getActivity(), items);
        ListView listView = (ListView) root.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = items.get(i);
                Intent intentPost=new Intent(getActivity(), BookPageActivity.class);

                BookPageActivity.pageTitle = item.getHeading();
                startActivity(intentPost);


            }
        });
        return root;
    }
}