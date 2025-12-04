package com.example.retrofit.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("quote")
    val quote: Quote
) : Serializable


data class Quote(
    @SerializedName("USD")
    val usd: USD
) : Serializable


data class USD(

    @SerializedName("price")
    val price: Double,

    @SerializedName("percent_change_24h")
    val percentChange24h: Double,

    @SerializedName("market_cap")
    val marketCap: Double
) : Serializable