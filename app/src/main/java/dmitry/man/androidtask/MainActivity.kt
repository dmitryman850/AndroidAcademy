package dmitry.man.androidtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container_activity_main_top, FragmentMoviesList())
                    .commit()
            }
        }
    }

    override fun toFragmentMoviesDetails() {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            add(
                R.id.container_activity_main_top, FragmentMoviesDetails()
            )
                .commit()
        }
    }
}