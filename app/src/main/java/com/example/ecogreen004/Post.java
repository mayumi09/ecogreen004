package com.example.ecogreen004;

public class Post {
    private String caption;
    private String imageUrl;
    private String userId;

    // Constructor (Empty for basic Firebase interaction)
    public Post() {
    }

    // Constructor with parameters
    public Post(String caption, String imageUrl, String userId) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    // Getters and Setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    // ... (Getters and setters for imageUrl and userId)
}
