package uz.instat.tmdb.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.instat.tmdb.data.repository.MoviesRepo
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.util.NetworkResult
import uz.instat.tmdb.framework.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {
    private val _responseMovieApi: MutableLiveData<NetworkResult<Movies>> =
        MutableLiveData()
    val responseMovieApi: LiveData<NetworkResult<Movies>> = _responseMovieApi

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

}