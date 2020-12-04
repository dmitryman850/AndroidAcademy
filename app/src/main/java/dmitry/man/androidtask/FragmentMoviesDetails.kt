package dmitry.man.androidtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class FragmentMoviesDetails: Fragment() {
    private var nameFilm: TextView? = null
    private var ageLimit: TextView? = null

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
        //nameFilm?.text = arguments?.getString("saveString")
    }

    companion object {
        fun newInstance(saveCount: String): FragmentMoviesDetails {
            val args = Bundle()
            args.putString("saveString", saveCount)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }
}