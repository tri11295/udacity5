package com.example.android.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("profile_path")
    val actorImage: String? = null
)
