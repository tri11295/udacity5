package com.example.android.moviedb.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieFavorite(
    var idMovie : Int,
    var title : String? = null ,
    var urlImg : String? = null
) : Parcelable
