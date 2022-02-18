package uz.instat.tmdb.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.instat.tmdb.data.network.MoviesApi
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.util.BaseApiResponse
import uz.instat.tmdb.framework.util.NetworkResult
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepo, BaseApiResponse() {

    override suspend fun getPopularMovies(): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { api.getPopularMovies() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchMovies(query: String): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { api.searchMovies(query) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiscoverMovies(): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { api.getDiscoverMovies() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieCredits(id: String): Flow<NetworkResult<MovieCredits>> {
        return flow<NetworkResult<MovieCredits>> {
            emit(safeApiCall { api.getMovieCredits(id) })
        }.flowOn(Dispatchers.IO)
    }

}

