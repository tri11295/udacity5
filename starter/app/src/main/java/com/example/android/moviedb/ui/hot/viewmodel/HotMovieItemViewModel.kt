package com.example.android.moviedb.ui.hot.viewmodel

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.android.moviedb.BR
import com.example.android.moviedb.data.model.MovieResult

class HotMovieItemViewModel(private val onClickListener: (Int) -> Unit) : BaseObservable() {

    @Bindable
    var movieResult: MovieResult? = null

    fun setData(movieResult: MovieResult?) {
        movieResult?.let {
            this.movieResult = it
            notifyPropertyChanged(BR.movieResult)
        }
    }

    fun onItemClicked(view: View) {
        movieResult?.let { item ->
            item.id?.let { id -> onClickListener(id) }
        }
    }
}
