package dmitry.man.androidtask

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dmitry.man.androidtask.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesDetails : Fragment() {
    private var backImage: ImageView? = null
    private var backText: TextView? = null
    private var fragmentMoviesDetailsClickListener: FragmentMoviesDetailsClickListener? = null
    private var backdropFilm: ImageView? = null
    private var actor1: ImageView? = null
    private var actor2: ImageView? = null
    private var actor3: ImageView? = null
    private var actor4: ImageView? = null
    private var actor1Name: TextView? = null
    private var actor2Name: TextView? = null
    private var actor3Name: TextView? = null
    private var actor4Name: TextView? = null
    private var nameFilm: TextView? = null
    private var ageLimit: TextView? = null
    private var descriptionFilm: TextView? = null
    private var genreFilm: TextView? = null
    private var reviews: TextView? = null
    private var starFilm1: ImageView? = null
    private var starFilm2: ImageView? = null
    private var starFilm3: ImageView? = null
    private var starFilm4: ImageView? = null
    private var starFilm5: ImageView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesDetailsClickListener) {
            fragmentMoviesDetailsClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backImage = view.findViewById(R.id.iv_back)
        backText = view.findViewById(R.id.btn_back_main_activity)
        backdropFilm = view.findViewById(R.id.top_background_movie_details)
        actor1 = view.findViewById(R.id.iv_actor_1_movie_details)
        actor2 = view.findViewById(R.id.iv_actor_2_movie_details)
        actor3 = view.findViewById(R.id.iv_actor_3_movie_details)
        actor4 = view.findViewById(R.id.iv_actor_4_movie_details)
        actor1Name = view.findViewById(R.id.tv_actors_1_movie_details)
        actor2Name = view.findViewById(R.id.tv_actors_2_movie_details)
        actor3Name = view.findViewById(R.id.tv_actors_3_movie_details)
        actor4Name = view.findViewById(R.id.tv_actors_4_movie_details)
        nameFilm = view.findViewById(R.id.tv_name_films_move_details)
        ageLimit = view.findViewById(R.id.tv_age_limit_movie_details)
        descriptionFilm = view.findViewById(R.id.descripsion_movie_details)
        genreFilm = view.findViewById(R.id.tv_genre_movie_details)
        reviews = view.findViewById(R.id.reviews_movie_details)
        starFilm1 = view.findViewById(R.id.iv_movies_list_star1)
        starFilm2 = view.findViewById(R.id.iv_movies_list_star2)
        starFilm3 = view.findViewById(R.id.iv_movies_list_star3)
        starFilm4 = view.findViewById(R.id.iv_movies_list_star4)
        starFilm5 = view.findViewById(R.id.iv_movies_list_star5)


        backImage?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }

        backText?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }

        val id = arguments?.getInt(KEY_FILM_ID) ?: 0

        val coroutine = CoroutineScope(Dispatchers.IO)

        coroutine.launch {
            val findFilm = getFilmById(id)
            bindingFilm(findFilm)
        }

    }

    @SuppressLint("SetTextI18n")
    private suspend fun bindingFilm(findFilm: Film?) = withContext(Dispatchers.Main) {
        backdropFilm?.let {
            Glide.with(this@FragmentMoviesDetails)
                .load(findFilm?.backdrop)
                .into(it)
        }
        nameFilm?.text = findFilm?.title
        ageLimit?.text = findFilm?.minimumAge.toString() + "+"
        descriptionFilm?.text = findFilm?.overview
        genreFilm?.text = findFilm?.genres?.joinToString(", ") { genre -> genre.name }
        reviews?.text = findFilm?.numberOfRatings.toString() + " REVIEWS"

            //TODO(нету актёров) подумать как можно обработать отсутствите данных
            // что-то по-умолчанию возможно и что делать когда есть 1 или 3 актёра а не 4
        findFilm?.let {
            for (i in 0..3) {
                if (findFilm.actors.isEmpty()) {
                    when (i) {
                        0 -> actor1Name?.text = "Нету данных"
                        1 -> actor2Name?.text = "Нету данных"
                        2 -> actor3Name?.text = "Нету данных"
                        3 -> actor4Name?.text = "Нету данных"
                    }
                } else {
                    when (i) {
                        0 -> {
                            actor1Name?.text = findFilm.actors[0].name
                            actor1?.let { it1 ->
                                Glide.with(this@FragmentMoviesDetails)
                                    .load(findFilm.actors[0].picture)
                                    .into(it1)
                            }
                        }
                        1 -> {
                            actor2Name?.text = findFilm.actors[1].name
                            actor2?.let { it1 ->
                                Glide.with(this@FragmentMoviesDetails)
                                    .load(findFilm.actors[1].picture)
                                    .into(it1)
                            }
                        }
                        2 -> {
                            actor3Name?.text = findFilm.actors[2].name
                            actor3?.let { it1 ->
                                Glide.with(this@FragmentMoviesDetails)
                                    .load(findFilm.actors[2].picture)
                                    .into(it1)
                            }
                        }
                        3 -> {
                            actor4Name?.text = findFilm.actors[3].name
                            actor4?.let { it1 ->
                                Glide.with(this@FragmentMoviesDetails)
                                    .load(findFilm.actors[3].picture)
                                    .into(it1)
                            }
                        }
                    }
                }
            }
        }
        view?.let { fillStar(findFilm) }

        }

    private fun fillStar(findFilm: Film?) {

        findFilm?.let {
            //TODO(обновление цикла) подумать как обновлять рейтинг с помощью цикла
            when (findFilm.ratings) {
                in 0.0..2.0 -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_empty)
                }
                in 2.0..4.0 -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_empty)
                }
                in 4.0..6.0 -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_empty)
                }
                in 6.0..8.0 -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_empty)
                }
                in 8.0..10.0 -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_full)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_full)
                }
                else -> {
                    starFilm1?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm2?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm3?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm4?.setImageResource(R.drawable.movie_details_star_empty)
                    starFilm5?.setImageResource(R.drawable.movie_details_star_empty)
                }

            }
        }
    }


    private suspend fun getFilmById(id: Int): Film? {
        return context?.let { it ->
            loadMovies(it).find {
                it.id == id
            }
        }
    }


    override fun onDetach() {
        super.onDetach()
        fragmentMoviesDetailsClickListener = null
    }

    companion object {
        fun newInstance(filmId: Int): FragmentMoviesDetails {
            val args = Bundle()
            args.putInt(KEY_FILM_ID, filmId)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }
}

interface FragmentMoviesDetailsClickListener {
    fun backClicked()
}

private const val KEY_FILM_ID = "KEY_FILM_ID"