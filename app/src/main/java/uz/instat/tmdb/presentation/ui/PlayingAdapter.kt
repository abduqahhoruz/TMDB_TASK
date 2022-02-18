package uz.instat.tmdb.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uz.instat.tmdb.R
import uz.instat.tmdb.data.network.NetworkConstants.BASE_IMG_URL
import uz.instat.tmdb.domein.model.Movies
import javax.inject.Inject

class PlayingAdapter @Inject constructor(
    private val manager: RequestManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    private var movieClicked: MovieClicked? = null

    fun setListener(movieClicked: MovieClicked) {
        this.movieClicked = movieClicked
    }

    private var movies: List<Movies.Result> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(movies: List<Movies.Result>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return PlayingHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val playingHolder = holder as PlayingHolder
        manager.load(BASE_IMG_URL + movies[position].posterPath)
            .into(playingHolder.img)
        playingHolder.txt.text = movies[position].originalTitle
        playingHolder.itemView.setOnClickListener {
            movieClicked?.movieClicked(movies[position].id)
        }
    }

    class PlayingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.movie_poster)
        val txt: TextView = view.findViewById(R.id.movie_name)
    }
}

interface MovieClicked {
    fun movieClicked(id: String)
}