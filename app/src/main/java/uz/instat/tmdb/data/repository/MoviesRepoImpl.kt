package uz.instat.tmdb.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.instat.tmdb.data.network.MovieDataSource
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import uz.instat.tmdb.framework.util.BaseApiResponse
import uz.instat.tmdb.framework.util.NetworkResult
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MoviesRepo, BaseApiResponse() {

    override suspend fun getPopularMovies(): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { movieDataSource.getPopularMovies() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchMovies(query: String): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { movieDataSource.searchMovies(query) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDiscoverMovies(): Flow<NetworkResult<Movies>> {
        return flow<NetworkResult<Movies>> {
            emit(safeApiCall { movieDataSource.getDiscoverMovies() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieCredits(id: String): Flow<NetworkResult<MovieCredits>> {
        return flow<NetworkResult<MovieCredits>> {
            emit(safeApiCall { movieDataSource.getMovieCredits(id) })
        }.flowOn(Dispatchers.IO)
    }

}

