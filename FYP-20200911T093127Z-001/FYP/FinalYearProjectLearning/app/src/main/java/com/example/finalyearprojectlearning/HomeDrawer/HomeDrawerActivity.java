package com.example.finalyearprojectlearning.HomeDrawer;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalyearprojectlearning.Fragments.ProfileFragment;
import com.example.finalyearprojectlearning.MainActivity;
import com.example.finalyearprojectlearning.R;
import com.example.finalyearprojectlearning.SumsungTry;
import com.example.finalyearprojectlearning.WelcomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FirebaseUser user;
    private ImageView iv;
    private TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Profile making
        iv=(ImageView)findViewById(R.id.navheader_imageView);
        tv1=(TextView)findViewById(R.id.navheader_imageViewforusername);
        tv2=(TextView)findViewById(R.id.navheader_textView);





        user=FirebaseAuth.getInstance().getCurrentUser();
        updateNavigationHeader();
    }

    private void updateNavigationHeader() {

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        TextView tv=headerView.findViewById(R.id.navheader_imageViewforusername);
        TextView tv1=headerView.findViewById(R.id.navheader_textView);
        final ImageView imageView=headerView.findViewById(R.id.navheader_imageView);
        if(user.getEmail()!=null)
            tv1.setText(user.getEmail());
        else
            tv1.setText(user.getPhoneNumber());
        tv.setText(user.getDisplayName());
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            startActivity(new Intent(this, SumsungTry.class));

        } else if (id == R.id.nav_gallery) {

            getSupportActionBar().setTitle("Profile");
            getSupportFragmentManager().beginTransaction().replace(R.id.container_frameLayout,new ProfileFragment()).commit();
        } else if (id == R.id.nav_slideshow) {

            FirebaseMessaging.getInstance().unsubscribeFromTopic("slideshow");
            FirebaseMessaging.getInstance().subscribeToTopic("slideshow")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Successful";
                            if (!task.isSuccessful()) {
                                msg = "Failed";
                            }
                            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

        } else if (id == R.id.nav_tools) {
            FirebaseMessaging.getInstance().subscribeToTopic("Tools")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Successful";
                            if (!task.isSuccessful()) {
                                msg = "Failed";
                            }
                            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

        } else if (id == R.id.nav_maps) {
            startActivity(new Intent(this, Notification.class));

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        if( view == iv || view == tv1 || view == tv2 ){
            ProfileCalling();
        }

    }

    private void ProfileCalling() {
        getSupportActionBar().setTitle("Profile");
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frameLayout,new ProfileFragment()).commit();
    }
}
