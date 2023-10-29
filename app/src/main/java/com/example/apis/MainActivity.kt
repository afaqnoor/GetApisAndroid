package com.example.apis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    lateinit var recycleView : RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.recyclerView)


        // this retrofitBuilder function work is get base Url , convert data json to kotlin language , call interface class
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        // this is data Get Function
        val retrofitData = retrofitBuilder.getProduct()

        /// Ctrl + shift + space they give a suggestion
        retrofitData.enqueue(object : Callback<MyProduct?> {
            override fun onResponse(call: Call<MyProduct?>, response: Response<MyProduct?>) {
                // if apis is success then use the data and Display it
                var responseBody = response.body()
                // we can assign a product to productList
                var productList = responseBody?.products!!
                // this is adapter call on main activity
                myAdapter = MyAdapter(this@MainActivity, productList)
                recycleView.adapter = myAdapter
                recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MyProduct?>, t: Throwable) {
                // if apis is Fails then can't work they can't work

                Log.d("Main Activity","onFailure: "+ t.message)
            }
        })
    }
}