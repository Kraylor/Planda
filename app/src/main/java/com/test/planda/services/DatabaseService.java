package com.test.planda.services;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.planda.models.LoggedInUser;

public class DatabaseService {

    private static DatabaseService service;
    private DatabaseReference database;
    private FirebaseUser currentUser;
    private RecyclerAdapter recyclerAdapter;
    private UserHandler userHandler;
    private static final String TAG = "DatabaseService";

    public DatabaseService() {
        database = FirebaseDatabase.getInstance("https://ikpmd-planda-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        addLoggedInUserEventListener(database);
        userHandler = UserHandler.getInstance();
        recyclerAdapter = RecyclerAdapter.getInstance();
    }

    public static DatabaseService getInstance() {
        if (service == null) {
            service = new DatabaseService();
        }
        return service;
    }

    public void writeUserData(LoggedInUser user) {
        database.child("users").child(this.currentUser.getUid()).setValue(user);
    }

    private void addLoggedInUserEventListener(DatabaseReference databaseReference) {
        ValueEventListener loggedInUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get object and use the values to update the UI
                LoggedInUser loggedInUser = dataSnapshot.getValue(LoggedInUser.class);
                userHandler.updateUser(loggedInUser);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(loggedInUserListener);
    }
}
