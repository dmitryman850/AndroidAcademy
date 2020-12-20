package dmitry.man.androidtask

import android.annotation.SuppressLint
import android.content.Context
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


    override fun getItemViewType(position: Int): Int {
        return when (films.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_FILMS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_list_empty, parent, false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_list, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(films[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(films[position])
                }
            }
            is EmptyViewHolder -> {
            }
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

abstract class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : FilmsViewHolder(itemView)
private class DataViewHolder(itemView: View) : FilmsViewHolder(itemView) {

    private val imageFilm: ImageView = itemView.findViewById(R.id.iv_film_item_movie_list)
    private val ageLimit: TextView = itemView.findViewById(R.id.tv_age_limit_item_movies_list)
    private val nameFilm: TextView = itemView.findViewById(R.id.tv_name_film_item_movies_list)
    private val genreFilm: TextView = itemView.findViewById(R.id.tv_genre_item_movies_list)
    private val reviewsFilm: TextView = itemView.findViewById(R.id.tv_reviews_item_movies_list)
    private val durationFilm: TextView = itemView.findViewById(R.id.tv_duration_film_item_movies_list)




    @SuppressLint("SetTextI18n")
    fun onBind(film: Film) {
        Glide.with(context)
            .load(film.poster)
            .into(imageFilm)

        fillStar(film, itemView)

        ageLimit.text = film.minimumAge.toString() + "+"
        nameFilm.text = film.title
        genreFilm.text = film.genres.joinToString(", ") { genre -> genre.name }
        reviewsFilm.text = film.numberOfRatings.toString() + " REVIEWS"
        durationFilm.text = film.runtime.toString() + " MIN"


    }
}

private fun fillStar(film: Film, itemView: View) {
    val starFilm1: ImageView = itemView.findViewById(R.id.iv_star1_item_movies_list)
    val starFilm2: ImageView = itemView.findViewById(R.id.iv_star2_item_movies_list)
    val starFilm3: ImageView = itemView.findViewById(R.id.iv_star3_item_movies_list)
    val starFilm4: ImageView = itemView.findViewById(R.id.iv_star4_item_movies_list)
    val starFilm5: ImageView = itemView.findViewById(R.id.iv_star5_item_movies_list)
    when (film.ratings) {
        in 0.0..1.9 -> {
            starFilm1.setImageResource(R.drawable.movies_list_star_full)
            starFilm2.setImageResource(R.drawable.movies_list_star_empty)
            starFilm3.setImageResource(R.drawable.movies_list_star_empty)
            starFilm4.setImageResource(R.drawable.movies_list_star_empty)
            starFilm5.setImageResource(R.drawable.movies_list_star_empty)
        }
        in 2.0..3.9 -> {
            starFilm1.setImageResource(R.drawable.movies_list_star_full)
            starFilm2.setImageResource(R.drawable.movies_list_star_full)
            starFilm3.setImageResource(R.drawable.movies_list_star_empty)
            starFilm4.setImageResource(R.drawable.movies_list_star_empty)
            starFilm5.setImageResource(R.drawable.movies_list_star_empty)
        }
        in 4.0..6.0 -> {
            starFilm1.setImageResource(R.drawable.movies_list_star_full)
            starFilm2.setImageResource(R.drawable.movies_list_star_full)
            starFilm3.setImageResource(R.drawable.movies_list_star_full)
            starFilm4.setImageResource(R.drawable.movies_list_star_empty)
            starFilm5.setImageResource(R.drawable.movies_list_star_empty)
        }
        in 6.0..7.9 -> {
            starFilm1.setImageResource(R.drawable.movies_list_star_full)
            starFilm2.setImageResource(R.drawable.movies_list_star_full)
            starFilm3.setImageResource(R.drawable.movies_list_star_full)
            starFilm4.setImageResource(R.drawable.movies_list_star_full)
            starFilm5.setImageResource(R.drawable.movies_list_star_empty)
        }
        in 8.0..10.0 -> {
            starFilm1.setImageResource(R.drawable.movies_list_star_full)
            starFilm2.setImageResource(R.drawable.movies_list_star_full)
            starFilm3.setImageResource(R.drawable.movies_list_star_full)
            starFilm4.setImageResource(R.drawable.movies_list_star_full)
            starFilm5.setImageResource(R.drawable.movies_list_star_full)
        }

    }
}

interface OnRecyclerItemClicked {
    fun onClick(film: Film)
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_FILMS = 1