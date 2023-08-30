package com.github.yeetologist.moviedb

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val description: String,
    val poster: Int,
    val genre: String
) : Parcelable
