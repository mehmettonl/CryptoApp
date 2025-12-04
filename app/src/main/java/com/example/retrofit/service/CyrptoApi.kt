package com.example.retrofit.service

import android.view.View
import com.example.retrofit.model.CryptoModel
import com.example.retrofit.model.CryptoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


    interface CyrptoApi {

        @Headers("X-CMC_PRO_API_KEY: YOUR_API_KEY")
        @GET("v1/cryptocurrency/listings/latest?limit=20")
        fun getData(): Call<CryptoResponse>

    }