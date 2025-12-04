package com.example.retrofit.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.adapter.RecyclerViewAdapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.CryptoModel
import com.example.retrofit.model.CryptoResponse
import com.example.retrofit.model.Quote
import com.example.retrofit.service.CyrptoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private lateinit var binding: ActivityMainBinding


    private val BASE_URL = "https://pro-api.coinmarketcap.com/"

    private var cryptoModel: ArrayList<CryptoModel>? = null
    private var cryptoPrice: ArrayList<Quote>?=null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view = binding.root



            val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
            binding.recyclerView.layoutManager = layoutManager


        loadData()

        }


    private fun loadData() {


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CyrptoApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<CryptoResponse>{

            override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<CryptoResponse>,
                response: Response<CryptoResponse>
            ) {
                if (response.isSuccessful) {

                    response.body().let {

                        val list = response.body()?.data ?: emptyList()
                        cryptoModel = ArrayList(list)

                        cryptoModel?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter


                        }

                    }







                    /*for (coin in cryptoModel!!) {
                        println(coin.name)
                        println(coin.quote.usd.price)
                    }*/
                }
            }
        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : " + cryptoModel.name,Toast.LENGTH_LONG).show()
    }
}

