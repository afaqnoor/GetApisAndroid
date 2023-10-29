package com.example.apis

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    // Fetch Data from Apis
    @GET("products")
    fun getProduct() : Call<MyProduct>


}