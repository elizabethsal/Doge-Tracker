package com.example.dogetracker.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dogetracker.R;
import com.example.dogetracker.fragments.ListFragment;
import com.example.dogetracker.fragments.MapFragment;
import com.example.dogetracker.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        //getSupportFragmentManager().beginTransaction().add(R.id.container, new MapFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapFragment()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.map:
                    fragment = new MapFragment();
                    break;

                case R.id.list:
                    fragment = new ListFragment();
                    break;

                case R.id.settings:
                    fragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();


            return true; //чтобы мы видели что иконка нажата
        });
}
}

