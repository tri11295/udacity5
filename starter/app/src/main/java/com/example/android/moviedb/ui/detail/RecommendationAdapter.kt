package com.example.android.moviedb.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.moviedb.data.model.MovieResult
import com.example.android.moviedb.databinding.ItemRecommendationsBinding
import com.example.android.moviedb.ultis.BindingDataRecyclerView
import com.example.android.moviedb.ultis.NoteDiffCallBack

class RecommendationAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<MovieResult, RecommendationAdapter.RecommendViewHolder>(NoteDiffCallBack()),
    BindingDataRecyclerView<MutableList<MovieResult>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding =
            ItemRecommendationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun setData(data: MutableList<MovieResult>?) {
        data?.let {
            submitList(data.toMutableList())
        }
    }

    class RecommendViewHolder(
        private val binding: ItemRecommendationsBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieResult) {
            binding.movieRecommend = movie
            binding.root.setOnClickListener{
                movie.id?.let { it1 -> onItemClick(it1) }
            }
            binding.executePendingBindings()
        }
    }
}
