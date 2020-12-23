package dmitry.man.androidtask.data

data class Film(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    var ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)