package com.example.android.moviedb.ui.hot.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.android.moviedb.data.model.MovieResult
import com.example.android.moviedb.databinding.ItemHotMovieBinding
import com.example.android.moviedb.databinding.ItemLoadMoreBinding
import com.example.android.moviedb.ui.hot.viewmodel.HotMovieItemViewModel

class HotMovieViewHolder(
    private val binding: ItemHotMovieBinding,
    private val onClickListener: (Int) -> Unit,
    private val itemViewModel: HotMovieItemViewModel = HotMovieItemViewModel(onClickListener)
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = itemViewModel
    }

    fun bindData(movieResult: MovieResult) {
        itemViewModel.setData(movieResult)
        binding.executePendingBindings()
    }
}

class LoadItemViewHolder(binding: ItemLoadMoreBinding) :
    RecyclerView.ViewHolder(binding.root)
