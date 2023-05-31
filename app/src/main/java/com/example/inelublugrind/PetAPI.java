package com.example.inelublugrind;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PetAPI {
    @POST("pet")

    Call<Pet> createPost(@Body Pet dataModal);
}