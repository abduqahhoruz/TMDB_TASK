package uz.instat.tmdb.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.instat.tmdb.data.network.NetworkConstants
import uz.instat.tmdb.domein.model.MovieCredits
import uz.instat.tmdb.domein.model.Movies

interface MoviesApi {

    @GET(NetworkConstants.POPULAR)
    suspend fun getPopularMovies(): Response<Movies>

    @GET(NetworkConstants.DISCOVER_MOVIE)
    suspend fun getDiscoverMovies(): Response<Movies>

    @GET(NetworkConstants.SEARCH)
    suspend fun searchMovies(
        @Query("query") query: String
    ): Response<Movies>

    @GET(NetworkConstants.MOVIE_CREDITS)
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: String
    ): Response<MovieCredits>



}