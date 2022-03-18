package com.jagl.zumati.provider

import com.jagl.zumati.Constants.API_BASE_URL
import com.jagl.zumati.model.Student
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

object Heroku {
    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(HerokuService::class.java)
}

interface HerokuService{
    @GET
    suspend fun getStudens(@Url url: String): List<Student>
}