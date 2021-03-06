package com.example.kriti;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.kriti.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FeedActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseDatabase firebaseDatabase;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = firebaseDatabase.getReference();
                final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("Users").child(currentuser).exists()){
                            Intent i =new Intent(FeedActivity.this,AddReading.class);
                            startActivity(i);
                        }
                        else if(dataSnapshot.child("ClubDept").child(currentuser).exists()){
                            AlertDialog.Builder alert = new AlertDialog.Builder(FeedActivity.this);
                            alert.setMessage("Upload").setCancelable(false).setPositiveButton("New Course", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent in = new Intent(FeedActivity.this,AddCourse.class);
                                    startActivity(in);
                                }
                            }).setNegativeButton("Video to Existing Course", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent =new Intent(FeedActivity.this,AddVideo.class);
                                    intent.putExtra("Title",dataSnapshot.child("ClubDept").child(currentuser).child("name").getValue().toString());
                                    startActivity(intent);
                                }
                            }).setNeutralButton("Reading material", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent =new Intent(FeedActivity.this,AddReading.class);
                                    startActivity(intent);
                                }
                            }).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.nav_app_bar_open_drawer_description,R.string.nav)

        final TextView NameTF = navigationView.getHeaderView(0).findViewById(R.id.NameTextField);
        firebaseDatabase =FirebaseDatabase.getInstance();
        DatabaseReference root = firebaseDatabase.getReference();
        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!currentuser.isEmpty()){
                    if(dataSnapshot.child("Users").child(currentuser).exists()) {
                        String name = dataSnapshot.child("Users").child(currentuser).child("name").getValue().toString();
                       if(!name.isEmpty())
                            NameTF.setText("Welcome, "+ name + "!");
                       // Toast.makeText(FeedActivity.this,username,Toast.LENGTH_LONG).show();
                    }
                    if(dataSnapshot.child("ClubDept").child(currentuser).exists()) {
                        String name = dataSnapshot.child("ClubDept").child(currentuser).child("name").getValue().toString();
                        if(!name.isEmpty())
                            NameTF.setText("Welcome, admin of "+name+"!");
                        // Toast.makeText(FeedActivity.this,username,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_gallery:
                        Intent i = new Intent(FeedActivity.this,ProfilePage.class);
                        startActivity(i);
                        break;
                    case R.id.nav_share:
                        AlertDialog.Builder alert = new AlertDialog.Builder(FeedActivity.this);
                        alert.setMessage("Are you sure you want to logout?").setCancelable(false).setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent in = new Intent(FeedActivity.this,SignInActivity.class);
                                startActivity(in);
                                Toast.makeText(FeedActivity.this,"Logged out Successfully",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

                        break;
                        case R.id.nav_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new HomeFragment()).commit();
                            //FeedActivity.super.onOptionsItemSelected(item);
                            break;
                    default:
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_gallery:
                Intent i = new Intent(FeedActivity.this,ProfilePage.class);
                return true;
            case R.id.nav_share:
                FirebaseAuth.getInstance().signOut();
                return true;
            case R.id.nav_home:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
