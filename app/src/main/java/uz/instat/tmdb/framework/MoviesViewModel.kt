package uz.instat.tmdb.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import uz.instat.tmdb.framework.util.NetworkResult
import uz.instat.tmdb.framework.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val favouritesRepo: FavouritesRepo
) : ViewModel() {

    private val _responseMovieApi: MutableLiveData<NetworkResult<Movies>> = MutableLiveData()
    val responseMovieApi: LiveData<NetworkResult<Movies>> = _responseMovieApi

    private val _isLiked = SingleLiveEvent<Boolean>()
    val isLiked: LiveData<Boolean> = _isLiked

    private val _responseMovieCredits = SingleLiveEvent<NetworkResult<MovieCredits>>()
    val responseMovieCredits: LiveData<NetworkResult<MovieCredits>> = _responseMovieCredits

    fun getPopularMovies() {
        viewModelScope.launch {
            moviesRepo.getPopularMovies().collect {
                _responseMovieApi.postValue(it)
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            moviesRepo.searchMovies(query).collect {
                _responseMovieApi.postValue(it)
            }
        }
    }

    fun getDiscoverMovies() {
        viewModelScope.launch {
            moviesRepo.getDiscoverMovies().collect {
                _responseMovieApi.postValue(it)
            }
        }
    }

    fun getMovie(id: String): Movies.Result? {
        var movie: Movies.Result? = null
        _responseMovieApi.value?.data?.results?.map { result ->

            if (result.id == id)
                movie = result
        }
        return movie
    }

    fun getMovieCredits(id: String) {
        viewModelScope.launch {
            moviesRepo.getMovieCredits(id).collect {
                _responseMovieCredits.postValue(it)
            }


        }
    }

    fun insertFavouriteMovie(
        id: String,
        name: String,
        url: String,
        release_date: String,
        voteCount: Double
    ) {
        viewModelScope.launch {
            try {
                favouritesRepo.insertFavourite(
                    FavouriteEntity(
                        id.toLong(),
                        name,
                        url,
                        release_date,
                        voteCount
                    )
                )
            } catch (e: Exception) {
                Timber.d("insertFavouriteMovie: " + e.message + " ")
            }
        }
    }

    fun checkIsLiked(id: String) {
        viewModelScope.launch {
            try {
                favouritesRepo.checkIfFavourite(id.toLong()).collect {
                    _isLiked.postValue(it)
                }
            } catch (e: Exception) {
                Timber.d("checkIsLiked: " + e.message)
            }
        }
    }

    fun deleteFavourite(id: String) {
        viewModelScope.launch {
            try {
                favouritesRepo.deleteFavourite(id.toLong())
            } catch (e: Exception) {
                Timber.d("deleteFavourite: " + e.message)
            }
        }
    }

}