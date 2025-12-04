package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

data class CryptoResponse(
    @SerializedName("data")
    val data: List<CryptoModel>
)