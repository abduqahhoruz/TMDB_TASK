package uz.instat.tmdb.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import uz.fizmasoft.dyhxx.base.BaseFragment
import uz.instat.tmdb.R
import uz.instat.tmdb.data.network.NetworkConstants
import uz.instat.tmdb.databinding.FragmentMovieInfoBinding
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.MoviesViewModel
import uz.instat.tmdb.framework.util.NetworkResult
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoFragment :
    BaseFragment<FragmentMovieInfoBinding>(FragmentMovieInfoBinding::inflate) {

    private val viewModel: MoviesViewModel by activityViewModels()
    private val args: MovieInfoFragmentArgs by navArgs()
    private var movie: Movies.Result? = null
    private var isLiked = false

    @Inject
    lateinit var manager: RequestManager

    @Inject
    lateinit var adapter: CreditsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = viewModel.getMovie(args.id)
        viewModel.getMovieCredits(args.id)
        viewModel.checkIsLiked(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
        setListeners()
    }

    private fun setupObservers() {
        viewModel.responseMovieCredits.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.cast?.let { it1 -> adapter.setList(it1) }
                }
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Error -> {

                }
            }
        }
        viewModel.isLiked.observe(viewLifecycleOwner) {
            if (it) {
                binding.ibLike.setImageResource(R.drawable.ic_like)
            } else {
                binding.ibLike.setImageResource(R.drawable.ic_unlike)
            }
            isLiked = it
        }
    }

    private fun setupViews() = with(binding) {
        recyclerCredits.adapter = adapter
        manager.load(NetworkConstants.BASE_IMG_URL + movie?.backdropPath)
            .into(imagePoster)
        tvTitle.text = movie?.title
        tvReleaseDate.text = movie?.releaseDate
        tvVoteAvarage.text = movie?.voteAverage.toString()
        tvOverview.text = movie?.overview
    }

    private fun setListeners() {
        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.ibLike.setOnClickListener {
            if (!isLiked) {
                movie?.let { it1 ->
                    viewModel.insertFavouriteMovie(
                        it1.id,
                        it1.originalTitle,
                        it1.posterPath,
                        it1.releaseDate,
                        it1.voteAverage
                    )
                }
                binding.ibLike.setImageResource(R.drawable.ic_like)
                isLiked = true
            } else {
                movie?.let { movie?.id?.let { it2 -> viewModel.deleteFavourite(it2) } }
                binding.ibLike.setImageResource(R.drawable.ic_unlike)
                isLiked = false
            }
        }

    }

}