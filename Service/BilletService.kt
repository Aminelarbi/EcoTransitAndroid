package com.example.ecotansit.Service;

import com.example.ecotansit.Constants
import com.example.ecotansit.models.BilletServiceResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface BilletService {

    @GET("/s/api/billets")
    suspend fun getAllBillets(): Response<BilletServiceResponse>

    companion object {
        fun create(): BilletService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(BilletService::class.java)
        }
    }
}
