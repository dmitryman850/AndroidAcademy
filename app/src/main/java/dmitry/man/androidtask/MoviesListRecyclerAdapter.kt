package dmitry.man.androidtask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesListRecyclerAdapter(
    context: Context?,
    private var films: List<Film>
    ) : RecyclerView.Adapter<MyViewHolder>() {


    override fun getItemCount(): Int {
        return films.size
    }

    // for position of Film of List
    private fun getItem(position: Int): Film {
        return films[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageFilm: ImageView = itemView.findViewById(R.id.iv_film_item_movie_list)
    private var ageLimit: TextView = itemView.findViewById(R.id.tv_age_limit_item_movies_list)
    private var nameFilm: TextView = itemView.findViewById(R.id.tv_name_film_item_movies_list)
    private var genreFilm: TextView = itemView.findViewById(R.id.tv_genre_item_movies_list)
    private var reviewsFilm: TextView = itemView.findViewById(R.id.tv_reviews_item_movies_list)
    //private var descriptionFilm: TextView = itemView.findViewById(R.id.description_movie_details)
    //private var imageActorOfFilm: ImageView = itemView.findViewById(R.id.iv_actor_1_movie_details)
    //private var nameActorOfFilm: TextView = itemView.findViewById(R.id.tv_actors_1_movie_details)

    fun bind(film: Film) {
        Glide.with(context)
            .load(film.imageFilm)
            .into(imageFilm)

//        Glide.with(context)
//            .load(film.imageActorOfFilm)
//            .into(imageActorOfFilm)

        ageLimit.text = film.ageLimit
        nameFilm.text = film.nameFilm
        genreFilm.text = film.genreFilm
        reviewsFilm.text = film.reviewsFilm
//        descriptionFilm.text = film.descriptionFilm
//        nameActorOfFilm.text = film.nameActorOfFilm
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
