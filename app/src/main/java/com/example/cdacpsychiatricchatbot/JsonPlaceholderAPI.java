package com.example.cdacpsychiatricchatbot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {
    @POST("response")
    Call<Post> createPost(@Body Post post);

}
