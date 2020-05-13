package com.goldengoals.retrofit3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.*

import com.goldengoals.retrofit3.R
import com.goldengoals.retrofit3.adapter.MovieAdapter
import com.goldengoals.retrofit3.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_main.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var movieListAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerMovie.layoutManager = GridLayoutManager(context, 2, VERTICAL, false)
        movieListAdapter = MovieAdapter()
        recyclerMovie.adapter = movieListAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        movieViewModel = ViewModelProvider(this)
            .get(MovieViewModel::class.java)
        movieViewModel.loadMovie()
        movieViewModel.topRatedMovie.observe(
            viewLifecycleOwner, Observer {
            recyclerMovie.visibility = View.VISIBLE
            movieListAdapter.updateResultList(it)
        })

    }

}
