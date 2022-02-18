package uz.instat.tmdb.data.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.util.NetworkResult

interface MoviesRepo {
    suspend fun getPopularMovies(): Flow<NetworkResult<Movies>>
    suspend fun searchMovies(query: String): Flow<NetworkResult<Movies>>
    suspend fun getDiscoverMovies(): Flow<NetworkResult<Movies>>
    suspend fun getMovieCredits(id:String): Flow<NetworkResult<MovieCredits>>
}