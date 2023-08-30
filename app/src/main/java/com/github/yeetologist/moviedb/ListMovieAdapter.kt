package com.github.yeetologist.moviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMovieAdapter (private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_movie_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_movie_desc)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_movie_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name,description,photo) = listMovie[position]
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.imgPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}