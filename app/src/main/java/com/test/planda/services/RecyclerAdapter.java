package com.test.planda.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.planda.R;
import com.test.planda.models.LoggedInUser;
import com.test.planda.models.TaskModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<TaskModel> tasklist;
    private static RecyclerAdapter recyclerAdapter;

    public RecyclerAdapter(ArrayList<TaskModel> tasklist) {
        this.tasklist = tasklist;
    }

    public static RecyclerAdapter getInstance() {
        if(recyclerAdapter == null) {
            LoggedInUser loggedInUser = LoggedInUser.getInstance();
            recyclerAdapter = new RecyclerAdapter(loggedInUser.getTasks());
        } return recyclerAdapter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        private TextView nameTextView;
        private TextView descriptionTextView;

        public MyViewHolder(final View view) {
            super(view);
            dateTextView = view.findViewById(R.id.date_show);
            nameTextView = view.findViewById(R.id.name_show);
            descriptionTextView = view.findViewById(R.id.description_show);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String date = tasklist.get(position).getDate();
        String name = tasklist.get(position).getName();
        String description = tasklist.get(position).getDescription();
        holder.dateTextView.setText(date);
        holder.nameTextView.setText(name);
        holder.descriptionTextView.setText(description);

    }

    @Override
    public int getItemCount() {
        try {
            return tasklist.size();
        } catch(NullPointerException e) {
            System.out.println(e);
            return 0;
        }
    }
}
