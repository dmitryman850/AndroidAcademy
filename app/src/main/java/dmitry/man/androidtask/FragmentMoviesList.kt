package dmitry.man.androidtask


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class FragmentMoviesList: Fragment() {
    private var fragmentMoviesListRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMoviesListRecyclerView = view.findViewById(R.id.recycler_view_fragment_movies_list)
        fragmentMoviesListRecyclerView?.adapter = MoviesListRecyclerAdapter(clickListener)
        fragmentMoviesListRecyclerView?.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onStart() {
        super.onStart()

        updateDate()
    }

    override fun onDetach() {
        super.onDetach()

        fragmentMoviesListRecyclerView = null
    }

    private fun updateDate() {
        (fragmentMoviesListRecyclerView?.adapter as? MoviesListRecyclerAdapter)?.apply {
            bindFilms(FilmsData().getFilmsData())
        }
    }

    private fun doOnClick(film: Film) {
        fragmentMoviesListRecyclerView?.let {
            Toast.makeText(context, "Вы выбрали ${film.nameFilm}", Toast.LENGTH_SHORT).show()
        }
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

