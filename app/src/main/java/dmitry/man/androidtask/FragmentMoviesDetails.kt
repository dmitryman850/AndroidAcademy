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

class FragmentMoviesDetails : Fragment() {
    private var backImage: ImageView? = null
    private var backText: TextView? = null
    private var fragmentMoviesDetailsClickListener: FragmentMoviesDetailsClickListener? = null

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
        val backdrop = view.findViewById<ImageView>(R.id.top_background_movie_details)

        backImage?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }

        backText?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }

        val id = arguments?.getInt(KEY_FILM_ID) ?: 0

        val coroutine = CoroutineScope(Dispatchers.IO)


        coroutine.launch(Dispatchers.Main) {
            getFilmById(id).apply {
                Glide.with(this@FragmentMoviesDetails)
                    .load(this?.backdrop)
                    .into(backdrop)
                view.findViewById<TextView>(R.id.tv_name_films_move_details)?.text =
                    this?.title
                view.findViewById<TextView>(R.id.tv_age_limit_movie_details)?.text =
                    this?.minimumAge.toString() + "+"
                view.findViewById<TextView>(R.id.descripsion_movie_details)?.text =
                    this?.overview
                view.findViewById<TextView>(R.id.tv_genre_movie_details)?.text =
                    this?.genres?.joinToString(", ") { genre -> genre.name }
                view.findViewById<TextView>(R.id.reviews_movie_details)?.text =
                    this?.numberOfRatings.toString() + " REVIEWS"
                fillStar(film = getFilmById(id), view)
            }
        }

    }

    private fun fillStar(film: Film?, itemView: View) {
        val starFilm1: ImageView = itemView.findViewById(R.id.iv_movies_list_star1)
        val starFilm2: ImageView = itemView.findViewById(R.id.iv_movies_list_star2)
        val starFilm3: ImageView = itemView.findViewById(R.id.iv_movies_list_star3)
        val starFilm4: ImageView = itemView.findViewById(R.id.iv_movies_list_star4)
        val starFilm5: ImageView = itemView.findViewById(R.id.iv_movies_list_star5)
        when (film!!.ratings) {
            in 0.0..2.0 -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_full)
                starFilm2.setImageResource(R.drawable.movie_details_star_empty)
                starFilm3.setImageResource(R.drawable.movie_details_star_empty)
                starFilm4.setImageResource(R.drawable.movie_details_star_empty)
                starFilm5.setImageResource(R.drawable.movie_details_star_empty)
            }
            in 2.0..4.0 -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_full)
                starFilm2.setImageResource(R.drawable.movie_details_star_full)
                starFilm3.setImageResource(R.drawable.movie_details_star_empty)
                starFilm4.setImageResource(R.drawable.movie_details_star_empty)
                starFilm5.setImageResource(R.drawable.movie_details_star_empty)
            }
            in 4.0..6.0 -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_full)
                starFilm2.setImageResource(R.drawable.movie_details_star_full)
                starFilm3.setImageResource(R.drawable.movie_details_star_full)
                starFilm4.setImageResource(R.drawable.movie_details_star_empty)
                starFilm5.setImageResource(R.drawable.movie_details_star_empty)
            }
            in 6.0..8.0 -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_full)
                starFilm2.setImageResource(R.drawable.movie_details_star_full)
                starFilm3.setImageResource(R.drawable.movie_details_star_full)
                starFilm4.setImageResource(R.drawable.movie_details_star_full)
                starFilm5.setImageResource(R.drawable.movie_details_star_empty)
            }
            in 8.0..10.0 -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_full)
                starFilm2.setImageResource(R.drawable.movie_details_star_full)
                starFilm3.setImageResource(R.drawable.movie_details_star_full)
                starFilm4.setImageResource(R.drawable.movie_details_star_full)
                starFilm5.setImageResource(R.drawable.movie_details_star_full)
            }
            else -> {
                starFilm1.setImageResource(R.drawable.movie_details_star_empty)
                starFilm2.setImageResource(R.drawable.movie_details_star_empty)
                starFilm3.setImageResource(R.drawable.movie_details_star_empty)
                starFilm4.setImageResource(R.drawable.movie_details_star_empty)
                starFilm5.setImageResource(R.drawable.movie_details_star_empty)
            }

        }
    }


    private suspend fun getFilmById(id: Int): Film? {
        return context?.let {
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