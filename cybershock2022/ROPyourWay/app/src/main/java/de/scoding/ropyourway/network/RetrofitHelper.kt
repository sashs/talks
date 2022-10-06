package de.scoding.ropyourway.network.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "http://127.0.0.1:8080/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}