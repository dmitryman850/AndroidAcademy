package dmitry.man.androidtask

data class Film(
    val filmId: Int,
    val imageFilm: Int,
    val ageLimit: String,
    val nameFilm: String,
    val genreFilm: String,
    val reviewsFilm: String,
    val descriptionFilm: String,
    val imageActorOfFilm: Int,
    val nameActorOfFilm: String,
)