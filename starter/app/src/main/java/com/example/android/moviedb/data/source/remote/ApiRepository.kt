package com.example.android.moviedb.data.source.remote

import com.example.android.moviedb.data.model.DetailMovie
import com.example.android.moviedb.data.model.MovieResponse
import com.example.android.moviedb.data.model.VideoResponse
import retrofit2.Response

object ApiRepository : ApiService {

    private val apiService = RetrofitClient.getApiService()

    override suspend fun getHotMovie(
        typeHotMovie: String,
        page: Int
    ): Response<MovieResponse> {
        return apiService.getHotMovie(typeHotMovie = typeHotMovie, page = page)
    }

    override suspend fun getDetailMovie(id: Int): Response<DetailMovie> {
        return apiService.getDetailMovie(id)
    }

    override suspend fun getRecommendMovie(id: Int): Response<MovieResponse> {
        return apiService.getRecommendMovie(id)
    }

    override suspend fun getTrailer(id: Int): Response<VideoResponse> {
        return apiService.getTrailer(id)
    }

}