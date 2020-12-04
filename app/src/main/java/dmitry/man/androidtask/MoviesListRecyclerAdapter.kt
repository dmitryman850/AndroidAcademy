package dmitry.man.androidtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MoviesListRecyclerAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<FilmsViewHolder>() {

    private var films = listOf<Film>()

//    override fun getItemCount(position: Int): Int {
//        return when (films.size) {
//            0 -> VIEW_TYPE_EMPTY
//            else -> VIEW_TYPE_ACTORS
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return DataViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(films[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(films[position])
                }
            }
            is EmptyViewHolder -> {}
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

    fun bindFilms(newFilms: List<Film>) {
        films = newFilms
        notifyDataSetChanged()
    }
}

abstract class FilmsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View): FilmsViewHolder(itemView)
private class DataViewHolder(itemView: View): FilmsViewHolder(itemView) {

    private val imageFilm: ImageView = itemView.findViewById(R.id.iv_film_item_movie_list)
    private val ageLimit: TextView = itemView.findViewById(R.id.tv_age_limit_item_movies_list)
    private val nameFilm: TextView = itemView.findViewById(R.id.tv_name_film_item_movies_list)
    private val genreFilm: TextView = itemView.findViewById(R.id.tv_genre_item_movies_list)
    private val reviewsFilm: TextView = itemView.findViewById(R.id.tv_reviews_item_movies_list)
    //private val descriptionFilm: TextView =
    //private val imageActorOfFilm: ImageView =
    private val nameActorOfFilm: TextView? = null

    fun onBind(film: Film) {
        Glide.with(context)
            .load(film.imageFilm)
            .into(imageFilm)

        ageLimit.text = film.ageLimit
        nameFilm.text = film.nameFilm
        genreFilm.text = film.genreFilm
        reviewsFilm.text = film.reviewsFilm
        nameActorOfFilm?.text = film.nameFilm
    }
}

interface OnRecyclerItemClicked {
    fun onClick(film: Film)
}

private val RecyclerView.ViewHolder.context
get() = this.itemView.context