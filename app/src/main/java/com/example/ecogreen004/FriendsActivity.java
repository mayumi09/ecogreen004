package com.example.ecogreen004;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private Button generateFriendsButton;
    private RecyclerView friendsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        generateFriendsButton = findViewById(R.id.generateFriendsButton);
        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);

        generateFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateFriends();
            }
        });
    }

    private void generateFriends() {
        // 1. Read JSON from assets
        String jsonData = readJsonFromAssets("user_data.json");

        // 2. Parse JSON with Gson
        Gson gson = new Gson();
        User[] users = gson.fromJson(jsonData, User[].class);

        // 3. Get current user's email (from Firebase)
        String currentUserEmail = null; // Declare here with initial value
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            currentUserEmail = firebaseUser.getEmail();
        } // else { ... } // You can still handle the no-user case

        // 4. Find current user's community
        String currentUserCommunity = findCommunityByEmail(users, currentUserEmail);

        // 5. Filter users with the same community
        List<User> potentialFriends = filterUsersByCommunity(users, currentUserCommunity);

        // 6. Display the emails of potential friends
        displayFriendEmails(potentialFriends);
    }

    private String findCommunityByEmail(User[] users, String currentUserEmail) {
        for (User user : users) {
            if (user.email.equals(currentUserEmail)) {
                return user.finalCommunity;
            }
        }

        // If the user email is not found:
        return null; // Or you could throw an exception if preferred
    }

    private void displayFriendEmails(List<User> friends) {
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsRecyclerView.setAdapter(new FriendAdapter(friends));
        friendsRecyclerView.setVisibility(View.VISIBLE); // Show the RecyclerView
        generateFriendsButton.setVisibility(View.GONE); // Hide the button
    }

    // FriendAdapter Class
    public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
        private List<User> friendsList;

        public FriendAdapter(List<User> friends) {
            this.friendsList = friends;
        }

        public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.button_layout, parent, false);
            return new FriendViewHolder(itemView);
        }

        public void onBindViewHolder(FriendViewHolder holder, int position) {
            User friend = friendsList.get(position);
            holder.friendButton.setText(friend.email);
        }

        public int getItemCount() {
            return friendsList.size();
        }

        // ViewHolder class to hold references to the item views
        class FriendViewHolder extends RecyclerView.ViewHolder {
            Button friendButton;

            public FriendViewHolder(View itemView) {
                super(itemView);
                friendButton = itemView.findViewById(R.id.friendButton);
            }
        }
    }

    private List<User> filterUsersByCommunity(User[] users, String community) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.finalCommunity.equals(community)) {
                result.add(user);
            }
        }
        return result;
    }
    private String readJsonFromAssets(String filename) {
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
