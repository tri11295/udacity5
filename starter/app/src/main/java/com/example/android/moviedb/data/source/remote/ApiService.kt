package com.example.android.moviedb.data.source.remote

import com.example.android.moviedb.data.model.ActorDetail
import com.example.android.moviedb.data.model.ActorResponse
import com.example.android.moviedb.data.model.DetailMovie
import com.example.android.moviedb.data.model.MovieResponse
import com.example.android.moviedb.data.model.VideoResponse
import com.example.android.moviedb.ultis.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{typeHotMovie}")
    suspend fun getHotMovie(
        @Path("typeHotMovie") typeHotMovie: String,
        @Query("page") page: Int
    ): Response<MovieResponse>


    @GET("movie/{id}?")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): Response<DetailMovie>

    @GET("movie/{id}/recommendations?")
    suspend fun getRecommendMovie(
        @Path("id") id: Int,
    ): Response<MovieResponse>

    @GET("movie/{id}/videos?")
    suspend fun getTrailer(
        @Path("id") id : Int,
    ): Response<VideoResponse>

    @GET("movie/{id}/credits?")
    suspend fun getActor(
        @Path("id") id: Int
    ): Response<ActorResponse>

    @GET("person/{idActor}")
    suspend fun getActorDetail(
        @Path("idActor") idActor: Int
    ): Response<ActorDetail>
}