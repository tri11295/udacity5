package com.example.android.moviedb.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.moviedb.data.model.MovieResult

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieFavorite")
    fun getFavoriteMovie(): LiveData<List<MovieResult>>

    @Query("SELECT * FROM movieFavorite WHERE id = :id")
    suspend fun checkFavorite(id: Int): MovieResult?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavoriteMovie(movie: MovieResult)

    @Query("DELETE FROM movieFavorite WHERE id = :movieId")
    fun unSaveFavoriteMovie(movieId: Int)

}