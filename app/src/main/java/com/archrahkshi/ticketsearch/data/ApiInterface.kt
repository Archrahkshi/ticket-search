package com.archrahkshi.ticketsearch.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL =
    "https://raw.githubusercontent.com/Archrahkshi/ticket-search/refs/heads/main/app/src/main/res/raw/"

@OptIn(ExperimentalSerializationApi::class)
val apiClient by lazy {
    Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        val json = Json { namingStrategy = SnakeCase }
        addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
    }.build().create<ApiInterface>()
}

interface ApiInterface {
    @GET("offers.json")
    suspend fun getOffers(): Offers
}
