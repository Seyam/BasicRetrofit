package com.example.basicretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {
    @GET("posts")
    Call<List<Posts>> getPosts();
}
