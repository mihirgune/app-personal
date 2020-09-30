package com.example.cdacpsychiatricchatbot;

public class Post {

    //The class post is used to define the data that will be sent to and from the Android app

    private String userQuery;

    public Post(String userQuery) {
        this.userQuery = userQuery;
    }

    public String getUserQuery() {
        return userQuery;
    }
}
