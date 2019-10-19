package com.example.kriti.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kriti.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GalleryFragment extends Fragment {
    TextView Name,Roll;
    private GalleryViewModel galleryViewModel;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_gallery, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Name = view.findViewById(R.id.nameField);
        Roll = view.findViewById(R.id.RollField);
        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference DatabaseRoot = FirebaseDatabase.getInstance().getReference();
        DatabaseRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(currentuser).exists()){
                    String name = dataSnapshot.child("Users").child(currentuser).child("name").getValue().toString();
                    String roll = dataSnapshot.child("Users").child(currentuser).child("roll").getValue().toString();
                    if(!name.isEmpty())
                        Name.setText(name);
                    if(!roll.isEmpty())
                        Roll.setText(roll);
                }
                else if(dataSnapshot.child("ClubDept").child(currentuser).exists()){
                    String name = dataSnapshot.child("ClubDept").child(currentuser).child("name").getValue().toString();
                    if(!name.isEmpty())
                        Name.setText(name);
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}