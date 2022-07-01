package com.example.myApp.data.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mocki.io"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface OpenTibiaApiService {
    @GET("v1/4fdc55db-f9e6-402f-b833-7abb7090fcb3")
    suspend fun getCreature(): List<SourceCreature>
}

object OpenTibiaApi {
    val retrofitService: OpenTibiaApiService by lazy{
        retrofit.create(OpenTibiaApiService::class.java)
    }
}