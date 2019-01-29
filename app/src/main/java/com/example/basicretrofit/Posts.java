package com.example.basicretrofit;

import com.google.gson.annotations.SerializedName;

public class Posts {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;  //variable name and jason key differs. so use annotation to specify


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}