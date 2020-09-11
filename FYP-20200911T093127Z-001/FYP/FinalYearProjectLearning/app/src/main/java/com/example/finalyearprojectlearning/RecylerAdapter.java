package com.example.finalyearprojectlearning;

import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {

    private ArrayList<Stories> storiesArrayList;
    public RecylerAdapter(ArrayList<Stories> stories) {
        this.storiesArrayList=stories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.samsungtryitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);


        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stories s=storiesArrayList.get(position);
        holder.textView.setText((CharSequence) s.getTextView());
        holder.imageView.setImageAlpha(s.getImageView());

    }

    @Override
    public int getItemCount() {
        return storiesArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView,initial;
        public ImageView imageView;
        public Stories stories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.samsungtrytext);
            imageView=(ImageView)itemView.findViewById(R.id.circularImage);

        }
    }
}
