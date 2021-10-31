package com.test.planda.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.test.planda.R;
import com.test.planda.activities.WelcomeActivity;
import com.test.planda.models.LoggedInUser;
import com.test.planda.services.DatabaseService;
import com.test.planda.services.UserHandler;

public class LogoutFragment extends Fragment {

    private static LogoutFragment fragment;

    public LogoutFragment() {
        // Required empty public constructor
    }

    public static LogoutFragment getInstance() {
        if (fragment == null) {
            fragment = new LogoutFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout, container, false);


        Button button = view.findViewById(R.id.fragment_logout_button);
        button.setOnClickListener(buttonView -> {
            DatabaseService service = DatabaseService.getInstance();
            service.writeUserData(LoggedInUser.getInstance());
            UserHandler userHandler = UserHandler.getInstance();
            userHandler.logout();
            Intent intent = new Intent(getActivity(),WelcomeActivity.class);
            startActivity(intent);
        });

        return view;
    }
}