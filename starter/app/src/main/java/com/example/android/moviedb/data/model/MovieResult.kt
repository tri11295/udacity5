package com.example.android.moviedb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movieFavorite")
data class MovieResult(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = null,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)
