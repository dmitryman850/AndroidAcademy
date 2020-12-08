package dmitry.man.androidtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {
    private var backImage: ImageView? = null
    private var backText: TextView? = null
    private var fragmentMoviesDetailsClickListener: FragmentMoviesDetailsClickListener? = null
    private var data = FilmsData()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backImage = view.findViewById(R.id.iv_back)
        backText = view.findViewById(R.id.btn_back_main_activity)

        backImage?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }
        backText?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }

        val filmId = arguments?.getInt(KEY_FILM_ID) ?: 0
        data.getFilmById(filmId)?.apply {
            view.findViewById<TextView>(R.id.tv_name_films_move_details)?.text = this.nameFilm
            view.findViewById<TextView>(R.id.tv_age_limit_movie_details)?.text = this.ageLimit
            view.findViewById<TextView>(R.id.descripsion_movie_details)?.text = this.descriptionFilm
            view.findViewById<TextView>(R.id.tv_genre_movie_details)?.text = this.genreFilm
            view.findViewById<TextView>(R.id.reviews_movie_details)?.text = this.reviewsFilm
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