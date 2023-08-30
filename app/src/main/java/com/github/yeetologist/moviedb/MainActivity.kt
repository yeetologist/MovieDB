package com.github.yeetologist.moviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.yeetologist.moviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var rvMovies: RecyclerView
    private var list = ArrayList<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvMovies = findViewById(R.id.rv_movie)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        rvMovies.adapter = listMovieAdapter

        listMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val moveToSelectedMovie = Intent(this@MainActivity,SelectedMovieActivity::class.java)
                moveToSelectedMovie.putExtra(SelectedMovieActivity.EXTRA_NAME,data.name)
                moveToSelectedMovie.putExtra(SelectedMovieActivity.EXTRA_DESCRIPTION,data.description)
                moveToSelectedMovie.putExtra(SelectedMovieActivity.EXTRA_GENRE,data.genre)
                moveToSelectedMovie.putExtra(SelectedMovieActivity.EXTRA_IMAGE,data.poster)
                startActivity(moveToSelectedMovie)
            }

        })
    }

    private fun getListHeroes(): ArrayList<Movie> {
        val arrListRet = ArrayList<Movie>()
        val dataName = resources.getStringArray(R.array.movies_name)
        val dataDesc = resources.getStringArray(R.array.movies_description)
        val dataPhoto = resources.obtainTypedArray(R.array.movies_poster)
        val dataGenre = resources.getStringArray(R.array.movies_genres)
        for (i in dataName.indices) {
            val dataTmp = Movie(dataName[i],dataDesc[i],dataPhoto.getResourceId(i,-1),dataGenre[i])
            arrListRet.add(dataTmp)
        }
        dataPhoto.recycle()
        return arrListRet
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> startActivity(Intent(this@MainActivity,AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}