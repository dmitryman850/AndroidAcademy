package dmitry.man.androidtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {

    private var mCardViewMovieList: CardView? = null
    private var mFragmentMoviesListClickListener: FragmentMoviesListClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListClickListener) {
            mFragmentMoviesListClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            mCardViewMovieList = view.findViewById<CardView>(R.id.card_view_film_container)
            mCardViewMovieList?.apply {
                setOnClickListener { mFragmentMoviesListClickListener?.toFragmentMoviesDetails() }
            }
    }



    override fun onDetach() {
        super.onDetach()
        mFragmentMoviesListClickListener = null
    }
}

interface FragmentMoviesListClickListener {
    fun toFragmentMoviesDetails()
}