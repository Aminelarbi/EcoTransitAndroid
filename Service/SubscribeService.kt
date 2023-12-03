package com.example.ecotansit.Service

import com.example.ecotansit.Constants
import com.example.ecotansit.models.SubscribeServiceResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SubscribeService {

    @GET("/s/api/subscribes")
    suspend fun getAllSubscribes(): Response<SubscribeServiceResponse>

    companion object {
        fun create(): SubscribeService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(SubscribeService::class.java)
        }
    }
}