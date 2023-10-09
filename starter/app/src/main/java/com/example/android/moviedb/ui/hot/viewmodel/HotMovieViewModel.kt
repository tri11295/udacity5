package com.example.android.moviedb.ui.hot.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.moviedb.data.model.MovieResult
import com.example.android.moviedb.data.source.local.AppDatabase
import com.example.android.moviedb.data.source.local.MovieDao
import com.example.android.moviedb.extensions.plusAssign
import com.example.android.moviedb.data.source.remote.ApiRepository
import com.example.android.moviedb.ultis.Constant
import com.example.android.moviedb.ultis.HotMovieType
import com.example.android.moviedb.ultis.LoadMoreRecyclerViewListener
import kotlinx.coroutines.launch

class HotMovieViewModel : ViewModel(), LoadMoreRecyclerViewListener {

    private val movieRepository = ApiRepository

    private var currentPage = Constant.DEFAULT_PAGE

    private val _typeHotMovie = MutableLiveData(HotMovieType.POPULAR.path)
    val typeHotMovie: MutableLiveData<String>
        get() = _typeHotMovie

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad: LiveData<Boolean>
        get() = _isLoad

    private val _movies = MutableLiveData<MutableList<MovieResult?>>()
    val movieResult: MutableLiveData<MutableList<MovieResult?>>
        get() = _movies

    init {
        fetchDataHotMovie(Constant.DEFAULT_PAGE, typeHotMovie.value.orEmpty())
    }

    override fun onLoadData() {
        _isLoad.value = true
        addLoadingMovie()
        Handler(Looper.getMainLooper()).postDelayed({
            removeLoadingMovie()
            currentPage++
            typeHotMovie.value?.let {
                fetchDataHotMovie(currentPage, it)
            }
            _isLoad.value = false
        }, Constant.DELAY_SECOND)
    }

    fun fetchDataHotMovie(
        numberPage: Int = Constant.DEFAULT_PAGE,
        typeHotMovie: String
    ) {
        viewModelScope.launch {
            try {
                movieRepository.getHotMovie(typeHotMovie, numberPage).apply {
                    body()?.let {
                        _movies.plusAssign(it.results)
                    }
                }
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
    }

    private fun addLoadingMovie() {
        _movies.plusAssign(MovieResult())
    }

    fun addHotMovieChange(typeHotMovie: HotMovieType) {
        _typeHotMovie.postValue(typeHotMovie.path)
        currentPage = Constant.DEFAULT_PAGE
        _movies.value?.clear()
    }

    private fun removeLoadingMovie() {
        if (_isLoad.value == true) {
            _movies.value?.let {
                it.removeAt(it.size - 1)
            }
        }
    }
}
