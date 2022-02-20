package uz.instat.tmdb.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.fizmasoft.dyhxx.base.BaseFragment
import uz.instat.tmdb.databinding.FragmentMoviesBinding
import uz.instat.tmdb.framework.MoviesViewModel
import uz.instat.tmdb.framework.util.DebouncingQueryTextListener
import uz.instat.tmdb.framework.util.NetworkResult
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {
    private val viewModel: MoviesViewModel by activityViewModels()

    @Inject
    lateinit var adapter: PlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDiscoverMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playingMovies.adapter = adapter
        adapter.setListener(object : MovieClicked {
            override fun movieClicked(id: String) {
                findNavController().navigate(MoviesFragmentDirections.actionMoviesToMovieInfo(id))
            }
        })
        viewModel.responseMovieApi.observe(viewLifecycleOwner) { it ->
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.let { it1 -> adapter.setList(it1.results) }
                }
                is NetworkResult.Error->{}
                is NetworkResult.Loading->{}
            }
        }
        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this@MoviesFragment.lifecycle
            ) { newText ->
                newText?.let { it ->
                    if (it.isEmpty()) {
                        viewModel.getDiscoverMovies()
                    } else {
                        viewModel.searchMovies(it)
                    }
                }
            }
        )
    }


}