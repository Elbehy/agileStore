package com.pfe.agilestore;

import android.graphics.PostProcessor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface userService {

    @POST("/api/login_check")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/api/users")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
