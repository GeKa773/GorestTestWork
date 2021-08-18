package com.gekaradchenko.goresttestwork.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://gorest.co.in/public-api/"
private const val GET = "posts"
private const val PAGE = "page"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface GorestApiService {
    @GET(GET)
    fun getItemAsync(
        @Query(PAGE) page: Int,
    ): Deferred<NetModel>
}

object GorestApi {
    val retrofitInstance: GorestApiService by lazy {
        retrofit.create(GorestApiService::class.java)
    }
}