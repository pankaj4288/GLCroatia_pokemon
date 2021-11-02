package com.android.glcroatia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.glcroatia.R
import com.android.glcroatia.data.MovesArray
import kotlinx.android.synthetic.main.moves_layout.view.*

class MovesAdapter(var context: Context, private val moves: List<MovesArray>) : RecyclerView.Adapter<MovesAdapter.MovesHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesAdapter.MovesHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.moves_layout, parent, false)
        return MovesHolder(v)
    }

    override fun onBindViewHolder(holder: MovesAdapter.MovesHolder, position: Int) {
        holder.moveName.text = moves.get(position).move.name
    }

    override fun getItemCount(): Int {
        return moves.size
    }

    class MovesHolder(view: View) : RecyclerView.ViewHolder(view) {
        var moveName = view.move_name
    }

}
