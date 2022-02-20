package uz.instat.tmdb.data.network

import retrofit2.Response
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val api: MoviesApi) : MovieDataSource {
    override suspend fun getDiscoverMovies(): Response<Movies> {
        return api.getDiscoverMovies()
    }

    override suspend fun getPopularMovies(): Response<Movies> {
        return api.getPopularMovies()
    }

    override suspend fun searchMovies(query: String): Response<Movies> {
        return api.searchMovies(query)
    }

    override suspend fun getMovieCredits(id: String): Response<MovieCredits> {
        return api.getMovieCredits(id)
    }


}