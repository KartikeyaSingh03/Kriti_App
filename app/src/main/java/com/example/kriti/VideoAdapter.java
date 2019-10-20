package com.example.kriti;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class VideoAdapter extends ArrayAdapter<Item> {
    public VideoAdapter(Activity context, ArrayList<Item> items) {

        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_course_page, parent, false);
        }


        Item currentQuestion = getItem(position);
        TextView headingView = (TextView) listItemView.findViewById(R.id.headingTextView);
        headingView.setText(currentQuestion.getHeading());

        TextView descriptionView = (TextView) listItemView.findViewById(R.id.descriptionTextView);
        descriptionView.setText(currentQuestion.getDescription());
        return listItemView;

    }
}