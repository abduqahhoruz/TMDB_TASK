package uz.instat.tmdb.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import uz.fizmasoft.dyhxx.base.BaseFragment
import uz.instat.tmdb.data.network.NetworkConstants
import uz.instat.tmdb.databinding.FragmentMovieInfoBinding
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.MoviesViewModel
import uz.instat.tmdb.framework.util.NetworkResult
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoFragment : BaseFragment<FragmentMovieInfoBinding>(FragmentMovieInfoBinding::inflate),
    View.OnClickListener {

    private val viewModel: MoviesViewModel by activityViewModels()
    private val args: MovieInfoFragmentArgs by navArgs()

    private var movie: Movies.Result? = null

    @Inject
    lateinit var manager: RequestManager

    @Inject
    lateinit var adapter: CreditsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = viewModel.getMovie(args.id)
        viewModel.getMovieCredits(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume ")
    }
    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: ")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy: ")
    }
    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: ")
    }
    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: ")
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
    }

    private fun setupViews() = with(binding) {
        recyclerCredits.adapter = adapter
        imageBack.setOnClickListener(this@MovieInfoFragment)
        manager.load(NetworkConstants.BASE_IMG_URL + movie?.backdropPath)
            .into(imagePoster)
        tvTitle.text = movie?.title
        tvReleaseDate.text = movie?.releaseDate
        tvVoteAvarage.text = movie?.voteAverage.toString()
        tvOverview.text = movie?.overview
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                imageBack.id -> {
                    findNavController().popBackStack()
                }
                else -> {
                }
            }
        }
    }

}