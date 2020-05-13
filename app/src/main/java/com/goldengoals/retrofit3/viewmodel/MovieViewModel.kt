package com.goldengoals.retrofit3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goldengoals.retrofit3.api.MovieApi
import com.goldengoals.retrofit3.model.Movie
import com.goldengoals.retrofit3.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    var topRatedMovie: MutableLiveData<List<Result>> = MutableLiveData()
    var movieLoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getTopRatedMovie(apiKey: String): LiveData<List<Result>> = topRatedMovie
    fun getError(): LiveData<Boolean> = movieLoadError
    fun getLoading(): LiveData<Boolean> = loading

    private val movieApi: MovieApi = MovieApi()

    fun loadMovie() {
        loading.value = true
        val apiCall = movieApi.getTopRatedMovie("9ece26d33964555f84228561378e9649")

        apiCall.enqueue(object : Callback<Movie> {

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                println("ERROR EEE")
                movieLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                println("RESPONSE ${response.body().toString()}")
                response.isSuccessful.let {
                    loading.value = false
                    val resultList: List<Result> = response.body()?.results ?: emptyList()
                    topRatedMovie.value = resultList
                }
            }

        })
    }

}