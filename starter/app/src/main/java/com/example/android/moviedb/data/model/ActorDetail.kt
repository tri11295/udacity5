package com.example.android.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class ActorDetail(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val imageUrl: String? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("place_of_birth")
    val address: String? = null,
    @SerializedName("biography")
    val biography: String? = null
)
