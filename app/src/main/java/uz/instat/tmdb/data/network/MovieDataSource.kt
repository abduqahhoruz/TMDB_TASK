package uz.instat.tmdb.data.network

import retrofit2.Response
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies

interface MovieDataSource {
    suspend fun getDiscoverMovies() : Response<Movies>
    suspend fun getPopularMovies(): Response<Movies>
    suspend fun searchMovies(query: String): Response<Movies>
    suspend fun getMovieCredits(id: String): Response<MovieCredits>
}