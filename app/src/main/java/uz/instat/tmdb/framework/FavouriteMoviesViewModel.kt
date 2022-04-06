package uz.instat.tmdb.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.instat.tmdb.data.local.FavouriteEntity
import uz.instat.tmdb.data.repository.FavouritesRepo
import uz.instat.tmdb.data.repository.MoviesRepo
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.util.SingleLiveEvent
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavouriteMoviesViewModel @Inject constructor(
    private val favouritesRepo: FavouritesRepo,
) : ViewModel() {

    private val _responseAllFavouriteMovies = SingleLiveEvent<List<FavouriteEntity>>()
    val responseAllMovies: LiveData<List<FavouriteEntity>> = _responseAllFavouriteMovies

    fun insertMovie(favouriteEntity: FavouriteEntity) {
     viewModelScope.launch {
         try {
             favouritesRepo.insertFavourite(favouriteEntity)
         }
         catch (e: Exception) {
             Timber.d("insertMovie: $e ")
         }
     }
    }

    fun getAllFavourites() {
        viewModelScope.launch {

            try {
                favouritesRepo.getAllFavourites().collect {
                    _responseAllFavouriteMovies.postValue(it)
                }
            }
            catch (e: Exception){
                Timber.d("getAllFavourites: $e ")
            }

        }
    }

    fun checkIfFavourites(id: Long){
        viewModelScope.launch {
            favouritesRepo.checkIfFavourite(id)
        }
    }

    fun deleteFavourite(id: Long){
        viewModelScope.launch {
            favouritesRepo.deleteFavourite(id)
        }
    }
}