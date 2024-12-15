package com.archrahkshi.ticketsearch.core.network.di

import com.archrahkshi.ticketsearch.core.network.ApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy.Builtins.SnakeCase
import okhttp3.MediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

private const val BASE_URL =
    "https://raw.githubusercontent.com/Archrahkshi/ticket-search/refs/heads/main/app/src/main/assets/"

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            val json = Json { namingStrategy = SnakeCase }
            addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        }.build().create<ApiInterface>()
    }
}
