package uz.instat.tmdb.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.instat.tmdb.R
import uz.instat.tmdb.data.network.NetworkConstants
import uz.instat.tmdb.data.network.NetworkConstants.BASE_IMG_URL
import uz.instat.tmdb.databinding.ItemMovieCreditBinding
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.presentation.DataPrimeApp_GeneratedInjector
import javax.inject.Inject

class CreditsAdapter @Inject constructor(
    private val manager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMovieCreditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CreditsViewHolder(binding)
    }

    private var movieCredits: List<MovieCredits.Cast> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(movieCredits: List<MovieCredits.Cast>) {
        this.movieCredits = movieCredits
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val creditsViewHolder = holder as CreditsViewHolder

        manager.load(BASE_IMG_URL + movieCredits[position].profilePath)
            .into(creditsViewHolder.img)
        creditsViewHolder.txt.text = movieCredits[position].name
    }


    override fun getItemCount(): Int {
        return movieCredits.size
    }

    inner class CreditsViewHolder(binding: ItemMovieCreditBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val img: AppCompatImageView = itemView.findViewById(R.id.iv_credit_item)
        val txt: MaterialTextView = itemView.findViewById(R.id.tv_credit_item)
    }
}


