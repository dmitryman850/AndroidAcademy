package dmitry.man.androidtask

import dmitry.man.androidtask.data.Actor
import dmitry.man.androidtask.data.Genre

data class Film(
    val id: Int,
    val title: String,
    val overview: String, //new
    val poster: String,
    val backdrop: String, //new
    var ratings: Float, //new
    val numberOfRatings: Int, //new
    val minimumAge: Int,
    val runtime: Int, //new
    val genres: List<Genre>,
    val actors: List<Actor> //new
)