package dmitry.man.androidtask

class FilmsData {
    fun getFilmsData(): List<Film> {
        return listOf(
            Film(
                0, R.drawable.first_movie_list, "16+", "AVENGERS: END GAME",
                "Action, Shooter", "12500 REVIEWS", "It's a good film",
                R.drawable.robert, "Robert"
            ),
            Film(
                1, R.drawable.first_movie_list, "18+", "Snatch",
                " Comedy, Crime", "10700 REVIEWS", "Unscrupulous boxing promoters," +
                        " violent bookmakers, a Russian gangster, incompetent amateur robbers and supposedly Jewish" +
                        " jewelers fight to track down a priceless stolen diamond.",
                R.drawable.cris, "Cris"
            ),
            Film(
                2, R.drawable.first_movie_list, "12+", "Lock, Stock and Two Smoking Barrels",
                " Comedy, Crime", "100 REVIEWS", "A botched card game in London triggers" +
                        " four friends, thugs, weed-growers, hard gangsters, " +
                        "loan sharks and debt collectors to collide with each other in a series" +
                        " of unexpected events, all for the sake of weed, cash and two antique shotguns.",
                R.drawable.mark, "Mark"
            )
        )
    }

    fun getFilmById(id: Int): Film? {
        return getFilmsData().find { it.filmId == id }
    }
}