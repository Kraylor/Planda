package com.test.planda.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.planda.R;
import com.test.planda.activities.PrimaryActivity;
import com.test.planda.models.LoggedInUser;
import com.test.planda.models.TaskModel;
import com.test.planda.services.RecyclerAdapter;

import java.util.ArrayList;

public class AgendaFragment extends Fragment {

    private static AgendaFragment fragment;

    public AgendaFragment() {}

    public static AgendaFragment getInstance() {
        if(fragment == null) {
            fragment = new AgendaFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);

        LoggedInUser loggedInUser = LoggedInUser.getInstance();
        ArrayList<TaskModel> taskList = loggedInUser.getTasks();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(taskList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            View popupView = inflater.inflate(R.layout.add_task_window, null);
            int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
            final PopupWindow popupWindow = new PopupWindow(popupView, wrapContent, wrapContent, true );
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

            Button addTaskButton = popupView.findViewById(R.id.add_task_button);
            addTaskButton.setOnClickListener(popView -> {
                EditText inputDate = popupView.findViewById(R.id.task_date_input);
                String date = inputDate.getText().toString();
                EditText inputDescription = popupView.findViewById(R.id.task_description_input);
                String description = inputDescription.getText().toString();
                EditText inputName = popupView.findViewById(R.id.task_name_input);
                String name = inputName.getText().toString();


                loggedInUser.addTask(new TaskModel(
                        date,
                        description,
                        name
                ));
                popupWindow.dismiss();
                Toast.makeText(getContext(), "Task added", Toast.LENGTH_SHORT).show();
            });
        });

        return view;
    }
}