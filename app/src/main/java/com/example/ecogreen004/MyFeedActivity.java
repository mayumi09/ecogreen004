package com.example.ecogreen004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyFeedActivity extends AppCompatActivity {

    private RecyclerView postRecyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feed);

        postRecyclerView = findViewById(R.id.postRecyclerView);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Temporary placeholder data
        List<Post> posts = getPlaceholderPosts();
        postAdapter = new PostAdapter(posts);
        postRecyclerView.setAdapter(postAdapter);
    }

    private List<Post> getPlaceholderPosts() {
        List<Post> posts = new ArrayList<>();
        // Add a few sample posts. You'll replace this with your actual data fetching logic
        return posts;
    }
}
