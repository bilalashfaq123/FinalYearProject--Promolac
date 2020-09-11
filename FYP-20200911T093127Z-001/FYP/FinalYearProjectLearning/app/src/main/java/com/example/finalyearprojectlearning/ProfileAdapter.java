package com.example.finalyearprojectlearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {


    private String[] data;
    public ProfileAdapter(String[] data) {
        this.data=data;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= (View) inflater.inflate(R.layout.list_item_profile,parent,false);

        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

        String title=data[position];
        holder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    //viewHolder Class
    public class ProfileViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.RV_listItem_ImageView);
            textView=itemView.findViewById(R.id.RV_listItem_TextView);
        }
    }
}
