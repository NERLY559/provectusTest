package com.example.provectus.network;

import com.example.provectus.model.RandomUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/")
    Call<RandomUser> getExample();
}
