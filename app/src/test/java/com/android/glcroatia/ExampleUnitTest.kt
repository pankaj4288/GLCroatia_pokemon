package com.android.glcroatia

import com.android.glcroatia.retrofit.ApiInterface
import com.android.glcroatia.viewmodel.PokemonViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun fetchPokemonName() {
        assertEquals("diglett",ApiInterface.create().getDetails(50).execute().body()?.name)
    }

    @Test
    fun fetchPokemonMoveSize() {
        assertEquals(136,ApiInterface.create().getDetails(150).execute().body()?.moves?.size)
    }

    @Test
    fun fetchPokemonBackImageUrl() {
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/200.png",ApiInterface.create().getDetails(200).execute().body()?.sprites?.back_default)
    }

}
