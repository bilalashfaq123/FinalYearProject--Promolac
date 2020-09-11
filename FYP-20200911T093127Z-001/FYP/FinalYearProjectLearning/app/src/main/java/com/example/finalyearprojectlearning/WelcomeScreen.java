package com.example.finalyearprojectlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ch.hsr.geohash.GeoHash;

public class WelcomeScreen extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        button=(Button)findViewById(R.id.btnShift);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity::ShowSignInOptions();
//                MainActivity mainActivity=new MainActivity();
//                mainActivity.ShowSignInOptions();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        Location location=new Location("geohash");
        location.setLatitude(53.2030476);
        location.setLongitude(45.0324948);

        GeoHash hash = GeoHash.withCharacterPrecision(53.2030476,45.0324948,6);
        Toast.makeText(this,hash.toString(),Toast.LENGTH_LONG);


    }
}
