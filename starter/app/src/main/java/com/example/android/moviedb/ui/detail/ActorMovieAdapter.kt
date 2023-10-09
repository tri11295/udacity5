package com.example.android.moviedb.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.moviedb.R
import com.example.android.moviedb.data.model.Actor
import com.example.android.moviedb.databinding.ItemActorMovieBinding

class ActorMovieAdapter(private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ActorMovieAdapter.ActorMovieViewHolder>() {

    private var listActor = mutableListOf<Actor>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<Actor>) {
        listActor = list
        notifyDataSetChanged()
    }

    inner class ActorMovieViewHolder(private val binding: ItemActorMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.actor = actor
            binding.root.setOnClickListener {
                actor.id?.let {
                    onClick.invoke(actor.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorMovieViewHolder {
        return ActorMovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_actor_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listActor.count()
    }

    override fun onBindViewHolder(holder: ActorMovieViewHolder, position: Int) {
        holder.bind(listActor[position])
    }
}