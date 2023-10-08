package com.example.android.moviedb.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.moviedb.data.model.MovieResult
import com.example.android.moviedb.data.source.local.AppDatabase
import com.example.android.moviedb.data.source.local.MovieDao

class FavoriteViewModel : ViewModel() {

    private lateinit var movieDao: MovieDao

    val movieResult: LiveData<List<MovieResult>>
        get() = movieDao.getFavoriteMovie()

    fun initDataBase(context: Context) {
        val dataBase = AppDatabase.getDatabase(context)
        movieDao = dataBase.movieDao()
    }

}