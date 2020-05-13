package com.goldengoals.retrofit3.api


import com.goldengoals.retrofit3.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("top_rated")
    fun getTopRatedMovie(
        @Query("api_key")apiKey : String): Call<Movie>

}