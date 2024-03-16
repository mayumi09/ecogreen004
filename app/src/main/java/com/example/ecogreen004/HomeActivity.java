package com.example.ecogreen004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.navigation_post) {
                        startActivity(new Intent(HomeActivity.this, CreatePostActivity.class));
                        return true;
                    } else if (item.getItemId() == R.id.navigation_friends) {
                        startActivity(new Intent(HomeActivity.this, FriendsActivity.class));
                        return true;
                    } else if (item.getItemId() == R.id.navigation_feed) {
                        startActivity(new Intent(HomeActivity.this, MyFeedActivity.class));
                        return true;
                    }
                    return false;
                }
            };

}
