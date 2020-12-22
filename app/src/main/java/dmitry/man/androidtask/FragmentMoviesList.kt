package dmitry.man.androidtask


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dmitry.man.androidtask.data.Film
import dmitry.man.androidtask.data.loadMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesList: Fragment() {
    private var fragmentMoviesListRecyclerView: RecyclerView? = null
    private var fragmentMoviesListClickListener: FragmentMoviesListClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentMoviesListClickListener) {
            fragmentMoviesListClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMoviesListRecyclerView = view.findViewById(R.id.recycler_view_fragment_movies_list)
        fragmentMoviesListRecyclerView?.layoutManager = GridLayoutManager(context, 2)
       fragmentMoviesListRecyclerView?.adapter = MoviesListRecyclerAdapter(clickListener)


        // coroutine + IO
        val coroutine = CoroutineScope(Dispatchers.IO)
        coroutine.launch { updateData() }
    }

    override fun onDetach() {
        super.onDetach()

        fragmentMoviesListRecyclerView = null
        fragmentMoviesListClickListener = null
    }

    private suspend fun updateData() {
        context?.let {
            val movies = loadMovies(it)
            updateAdapter(movies)
        }
    }

    private suspend fun updateAdapter(movies: List<Film>) = withContext(Dispatchers.Main) {
        (fragmentMoviesListRecyclerView?.adapter as? MoviesListRecyclerAdapter)?.apply {
            bindFilms(movies)
        }
    }

    private fun doOnClick(film: Film) {
            //Toast.makeText(context, "Вы выбрали ${film.title} с ${film.overview}", Toast.LENGTH_SHORT).show()
            fragmentMoviesListClickListener?.toFragmentMoviesDetails(film.id)
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(film: Film) {
            doOnClick(film)
        }
    }

    companion object {
        fun newInstanse() = FragmentMoviesList()
    }

}

interface FragmentMoviesListClickListener {
    fun toFragmentMoviesDetails(filmId: Int)
}

