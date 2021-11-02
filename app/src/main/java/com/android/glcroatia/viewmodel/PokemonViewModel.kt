package com.android.glcroatia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.glcroatia.data.Pokemon
import com.android.glcroatia.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Response

class PokemonViewModel: ViewModel() {

    val TAG = "PokemonViewModel"

    private val POKEMON_START_NO = 1
    private val POKEMON_END_NO = 898

    private val _pokemonData = MutableLiveData<Pokemon>()
    val pokemonData: LiveData<Pokemon>
        get() = _pokemonData

    private var _serverError = MutableLiveData<Boolean>(false)
    val serverError: LiveData<Boolean>
        get() = _serverError

    val apiInterface by lazy {
        ApiInterface.create()
    }

    fun onRefreshClicked() {
        Log.d(TAG,"onRefreshClicked")
        fetchPokemon()
    }

    fun fetchPokemon() {
        val inter = apiInterface.getDetails((POKEMON_START_NO..POKEMON_END_NO).random())
        inter.enqueue( object : retrofit2.Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                Log.d(TAG, "result = "+response.body())
                _pokemonData.value = response.body()
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                _serverError.value = true
            }
        })
    }
}