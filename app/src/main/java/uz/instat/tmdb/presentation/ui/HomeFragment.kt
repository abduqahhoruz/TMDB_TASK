package uz.instat.tmdb.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.fizmasoft.dyhxx.base.BaseFragment
import uz.instat.tmdb.R
import uz.instat.tmdb.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        direct()
    }

    private fun direct() {
        with(binding){
            tvMovies.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
            }
            tvFavourites.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_favouritesFragment)
            }

        }
    }
}