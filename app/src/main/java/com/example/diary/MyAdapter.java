package com.example.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Post> postItems;

    public MyAdapter(Context context, ArrayList<Post> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post newPost = postItems.get(position);
        holder.date.setText(newPost.getTime());
        holder.title.setText(newPost.getTitle());
        holder.post.setText(newPost.getPost());
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView title;
        TextView post;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.datePost);
            title = itemView.findViewById(R.id.titlePost);
            post = itemView.findViewById(R.id.post);
        }
    }
}
