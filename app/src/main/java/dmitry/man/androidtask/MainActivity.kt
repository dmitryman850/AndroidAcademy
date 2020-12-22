package dmitry.man.androidtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener,
    FragmentMoviesDetailsClickListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_activity_main_top, FragmentMoviesList.newInstanse())
                .commit()
        }
    }

    override fun toFragmentMoviesDetails(filmId: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.container_activity_main_top, FragmentMoviesDetails.newInstance(filmId))
            .commit()
    }

    override fun backClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_activity_main_top, FragmentMoviesList())
            .commit()
    }


}