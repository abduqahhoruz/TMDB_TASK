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
import uz.instat.tmdb.data.local.FavouriteEntity
import uz.instat.tmdb.data.network.NetworkConstants.BASE_IMG_URL
import uz.instat.tmdb.domein.model.Movies
import javax.inject.Inject

class FavouritesAdapter @Inject constructor(
    private val manager: RequestManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    private var favouriteClicked: FavouriteClicked? = null

    fun setListener(favouriteClicked: FavouriteClicked) {
        this.favouriteClicked = favouriteClicked
    }

    private var favourites: List<FavouriteEntity> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(favourites: List<FavouriteEntity>) {
        this.favourites = favourites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavouriteHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return favourites.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val favouriteHolder = holder as FavouriteHolder
        manager.load(BASE_IMG_URL + favourites[position].image_url)
            .into(favouriteHolder.img)
        favouriteHolder.txt.text = favourites[position].name
        favouriteHolder.itemView.setOnClickListener {
            favouriteClicked?.favouriteClicked(favourites[position].id.toString())
        }
    }

    class FavouriteHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.movie_poster)
        val txt: TextView = view.findViewById(R.id.movie_name)
    }
}

interface FavouriteClicked {
    fun favouriteClicked(id: String)
}