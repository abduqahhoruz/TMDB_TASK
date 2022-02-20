package uz.instat.tmdb.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.fizmasoft.dyhxx.base.BaseFragment
import uz.instat.tmdb.databinding.FragmentFavouritesBinding
import uz.instat.tmdb.framework.FavouriteMoviesViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {
    @Inject
    lateinit var adapter: FavouritesAdapter
    private val viewModel: FavouriteMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllFavourites()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavourites.adapter = adapter
        viewModel.responseAllMovies.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
            adapter.setListener(object : FavouriteClicked {
                override fun favouriteClicked(id: String) {
                    findNavController().navigate(
                        FavouritesFragmentDirections.actionFavouritesFragmentToMovieInfoFragment(
                            id
                        )
                    )
                }

            })
        }
    }
