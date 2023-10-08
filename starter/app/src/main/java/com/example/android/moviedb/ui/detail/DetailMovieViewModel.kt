package com.example.android.moviedb.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.moviedb.data.model.DetailMovie
import com.example.android.moviedb.data.model.MovieFavorite
import com.example.android.moviedb.data.model.MovieResult
import com.example.android.moviedb.data.source.local.AppDatabase
import com.example.android.moviedb.data.source.local.MovieDao
import com.example.android.moviedb.data.source.remote.ApiRepository
import com.example.android.moviedb.extensions.plusAssign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class DetailMovieViewModel : ViewModel() {

    private lateinit var movieDao: MovieDao
    private val movieRepository = ApiRepository

    private val _genres = MutableLiveData("")
    val genres: LiveData<String>
        get() = _genres

    private val _isHideTittle = MutableLiveData(false)
    val isHideTittle: LiveData<Boolean>
        get() = _isHideTittle

    private val _isHideTagLine = MutableLiveData(false)
    val isHideTagLine: LiveData<Boolean>
        get() = _isHideTagLine

    private val _isHideReleaseDate = MutableLiveData(false)
    val isHideReleaseDate: LiveData<Boolean>
        get() = _isHideReleaseDate

    private val _detailMovie = MutableLiveData<DetailMovie?>()
    val detailMovie: LiveData<DetailMovie?>
        get() = _detailMovie

    private val _isFavoriteMovie = MutableLiveData<Boolean>()
    val isFavoriteMovie: LiveData<Boolean>
        get() = _isFavoriteMovie

    private val _recommendMovie = MutableLiveData<MutableList<MovieResult?>>()
    val recommendMovie: LiveData<MutableList<MovieResult?>>
        get() = _recommendMovie

    var keyYoutube: String? = null
    var isFavorite = false

    fun initDataBase(context: Context) {
        val dataBase = AppDatabase.getDatabase(context)
        movieDao = dataBase.movieDao()
    }

    fun getDetailMovie(idMovie: Int) {
        try {
            viewModelScope.launch {
                try {
                    launch {
                        movieRepository.getDetailMovie(idMovie).body()?.also {
                            _detailMovie.value = it
                            _genres.value = it.genres.joinToString(", ") { genre ->
                                genre.name
                            }
                            if (it.title.isNullOrEmpty()) {
                                _isHideTittle.value = true
                            }
                            if (it.tagLine.isNullOrEmpty()) {
                                _isHideTagLine.value = true
                            }
                            if (it.releaseDate.isNullOrEmpty()) {
                                _isHideReleaseDate.value = true
                            }
                        }
                    }
                    launch {
                        movieRepository.getRecommendMovie(idMovie).body()?.also {
                            _recommendMovie.plusAssign(it.results)
                        }
                    }
                    launch {
                        movieRepository.getTrailer(idMovie).body()?.also {
                            keyYoutube = it.results[0].key
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveMovie() {
        viewModelScope.launch {
            detailMovie.value?.let {
                isFavorite = true
                val decimalFormat = DecimalFormat("#.#")
                val roundedNumber = decimalFormat.format(it.rate) ?: "0.0"
                movieDao.saveFavoriteMovie(
                    MovieResult(
                        it.id,
                        it.title,
                        it.photoPoster,
                        roundedNumber.toDouble()
                    )
                )
            }
        }
    }

    fun unSaveMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            detailMovie.value?.let {
                isFavorite = false
                movieDao.unSaveFavoriteMovie(
                    it.id
                )
            }
        }
    }

    fun checkFavorite(idMovie: Int, action: (Boolean) -> Unit) {
        viewModelScope.launch {
            if (movieDao.checkFavorite(idMovie) != null) {
                isFavorite = true
            }
            action.invoke(isFavorite)
        }
    }
}
