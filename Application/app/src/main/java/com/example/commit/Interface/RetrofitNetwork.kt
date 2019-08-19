package com.example.commit.Interface

import retrofit2.Call
import retrofit2.http.GET
import java.lang.reflect.Array

interface RetrofitNetwork {

    @GET("/network")
    fun listUser() : Call<Array>
}