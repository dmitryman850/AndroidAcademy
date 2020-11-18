package dmitry.man.androidtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBtnToMovieDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnToMovieDetails = findViewById(R.id.btn_to_movie_details_activity)

        mBtnToMovieDetails.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            mBtnToMovieDetails -> {
                toMovieDetails()
            }
        }
    }

    private fun toMovieDetails() {
        startActivity(Intent(this, MovieDetailsActivity::class.java))
    }
}