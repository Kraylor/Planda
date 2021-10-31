package com.test.planda.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.test.planda.R;
import com.test.planda.fragments.AgendaFragment;
import com.test.planda.fragments.LogoutFragment;
import com.test.planda.models.LoggedInUser;

public class PrimaryActivity extends AppCompatActivity {

    private LoggedInUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        user = LoggedInUser.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.primary_container,
                AgendaFragment.getInstance()).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(user.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_item_agenda:
                fragment = AgendaFragment.getInstance();
                break;
            case R.id.menu_item_logout:
                fragment = LogoutFragment.getInstance();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.primary_container
                ,fragment).commit();
        return true;
    }
}