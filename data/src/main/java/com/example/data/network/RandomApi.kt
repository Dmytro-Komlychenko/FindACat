package com.example.data.network

import retrofit2.http.GET

interface RandomApi {
    @GET("integers/?num=1&min=0&max=1&col=1&base=10&format=plain&rnd=new")
    suspend fun getInt(): Int
}