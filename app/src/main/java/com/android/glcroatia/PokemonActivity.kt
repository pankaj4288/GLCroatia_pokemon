package com.android.glcroatia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.glcroatia.adapter.MovesAdapter
import com.android.glcroatia.databinding.ActivityPokemonBinding
import com.android.glcroatia.viewmodel.PokemonViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MovesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPokemonBinding = ActivityPokemonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.lifecycleOwner = this
        val model: PokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        binding.viewModel = model

        linearLayoutManager = LinearLayoutManager(this)
        moves_list.layoutManager = linearLayoutManager

        model.fetchPokemon()

        model.pokemonData.observe(this, Observer {
            name.text = getString(R.string.name) + " : "+ it.name
            moves.text = getString(R.string.moves) + " : "
            adapter = MovesAdapter(this,it.moves)
            moves_list.adapter = adapter
            Glide.with(this)
                .load(it.sprites.front_default)
                .into(front_image)
            Glide.with(this)
                .load(it.sprites.back_default)
                .into(back_image)
        })

        model.serverError.observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.server_error), Toast.LENGTH_LONG)
            }
        })
    }

}
