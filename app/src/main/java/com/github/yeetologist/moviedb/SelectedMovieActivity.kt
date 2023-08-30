package com.github.yeetologist.moviedb

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.yeetologist.moviedb.databinding.ActivitySelectedMovieBinding

class SelectedMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedMovieBinding

    companion object {
        const val EXTRA_NAME = "extra_movie"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_NAME)
        val desc = intent.getStringExtra(EXTRA_DESCRIPTION)
        val genre = intent.getStringExtra(EXTRA_GENRE)
        val image = intent.getIntExtra(EXTRA_IMAGE, 0)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        binding.tvMovieTitleSelected.text = title
        binding.tvMovieGenreSelected.text = genre
        binding.tvMovieDescriptionSelected.text = desc
        binding.imgMovieSelected.setImageResource(image)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share -> {
                val shareTitle = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,intent.getStringExtra(EXTRA_NAME))
                    type = "text/plain"
                }
                startActivity(shareTitle)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}