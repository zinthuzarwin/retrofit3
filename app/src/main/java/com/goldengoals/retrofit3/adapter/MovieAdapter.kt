package com.goldengoals.retrofit3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goldengoals.retrofit3.R
import com.goldengoals.retrofit3.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main.view.*

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var resultList: List<Result> = listOf()

    fun updateResultList(resultList: List<Result>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMovie(result: Result) {
            itemView.txtMovieTitle.text = result.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w200${result.poster_path}") //image link ko apply loat
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.movie_Image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bindMovie(resultList[position])
}