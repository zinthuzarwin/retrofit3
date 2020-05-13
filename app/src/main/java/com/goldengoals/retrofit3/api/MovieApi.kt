package com.goldengoals.retrofit3.api

import com.goldengoals.retrofit3.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {

    private val movieInterface: MovieInterface

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    //movie interface object ko create loat
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieInterface = retrofit.create(MovieInterface::class.java)
    }

    fun getTopRatedMovie(apiKey: String): Call<Movie> {
        return movieInterface.getTopRatedMovie(apiKey)
    }
}