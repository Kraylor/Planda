package com.test.planda.services;


import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.test.planda.activities.PrimaryActivity;
import com.test.planda.models.LoggedInUser;

public class UserHandler {

    private static UserHandler userHandler;
    private final FirebaseAuth firebaseAuth;

    public UserHandler() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void startPrimaryActivity(Context context) {
        Intent intent = new Intent(context, PrimaryActivity.class);
        context.startActivity(intent);
    }

    public static synchronized UserHandler getInstance() {
        if(userHandler == null) {
            userHandler = new UserHandler();
        }
        return userHandler;
    }

    public void updateUser(FirebaseUser user, String name) {
        LoggedInUser loggedInUser = LoggedInUser.getInstance();
        loggedInUser.setName(name);
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setUid(user.getUid());
    }

    public void updateUser(LoggedInUser user) {
        LoggedInUser loggedInUser = LoggedInUser.getInstance();
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setUid(user.getUid());
        loggedInUser.setName(user.getName());
        loggedInUser.setTasks(user.getTasks());
    }

    public void updateUser(FirebaseUser user) {
        LoggedInUser loggedInUser = LoggedInUser.getInstance();
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setUid(user.getUid());
    }

    public void logout() {
        firebaseAuth.signOut();
    }

}
