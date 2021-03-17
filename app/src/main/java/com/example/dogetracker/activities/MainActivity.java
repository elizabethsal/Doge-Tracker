package com.example.dogetracker.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dogetracker.R;
import com.example.dogetracker.fragments.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //getSupportFragmentManager().beginTransaction().add(R.id.container, new MapFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapFragment()).commit();
    }
}
