package com.android.glcroatia.retrofit

import com.android.glcroatia.data.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("pokemon/{id}")
    fun getDetails(@Path("id") id: Int): Call<Pokemon>

    companion object {
        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}