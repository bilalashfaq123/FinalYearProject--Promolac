package com.example.finalyearprojectlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.widget.GridLayout.HORIZONTAL;

public class SumsungTry extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecylerAdapter recylerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Stories> stories;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumsung_try);

        recyclerView=(RecyclerView)findViewById(R.id.RecylerViewSamsungTry);
        stories=new ArrayList<Stories>();
        stories.add(new Stories(R.drawable.com_facebook_profile_picture_blank_portrait,"Bilal"));
        stories.add(new Stories(R.drawable.com_facebook_profile_picture_blank_portrait,"Bilal1"));
        stories.add(new Stories(R.drawable.com_facebook_profile_picture_blank_portrait,"Bilal2"));
        stories.add(new Stories(R.drawable.com_facebook_profile_picture_blank_portrait,"Bilal3"));
        stories.add(new Stories(R.drawable.com_facebook_profile_picture_blank_portrait,"Bilal4"));
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recylerAdapter=new RecylerAdapter(stories);
        recyclerView.setAdapter(recylerAdapter);
    }
}
