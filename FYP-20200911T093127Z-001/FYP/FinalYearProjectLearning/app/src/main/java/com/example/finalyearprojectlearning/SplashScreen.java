package com.example.finalyearprojectlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalyearprojectlearning.HomeDrawer.HomeDrawerActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(10*100);

                    // After 5 seconds redirect to another intent
                    if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                        Intent i=new Intent(getBaseContext(), HomeDrawerActivity.class);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(getBaseContext(), WelcomeScreen.class);
                        startActivity(i);

                    }
                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}
