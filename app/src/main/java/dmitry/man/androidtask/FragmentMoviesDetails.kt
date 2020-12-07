package dmitry.man.androidtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class FragmentMoviesDetails: Fragment() {
    private var nameFilm: TextView? = null
    private var ageLimit: TextView? = null
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameFilm = view.findViewById(R.id.tv_name_films_move_details)
        backImage = view.findViewById(R.id.iv_back)
        backText = view.findViewById(R.id.btn_back_main_activity)
        backImage?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }
        backText?.setOnClickListener { fragmentMoviesDetailsClickListener?.backClicked() }
        //nameFilm?.text = arguments?.getString("saveString")
        val filmId = arguments?.getInt("KEY_FILM_ID")
        nameFilm?.text = filmId.toString()
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesDetailsClickListener = null
    }

    companion object {
        fun newInstance(filmId: Int): FragmentMoviesDetails {
            val args = Bundle()
            args.putInt("KEY_FILM_ID", filmId)
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