package uz.instat.tmdb.data.network

object NetworkConstants {
    const val BASE_URL = "https://api.themoviedb.org"
    const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"
    const val NETWORK_TIMEOUT = 20000L
    const val API_KEY = "200dfe85e028ea52415f100c2e408ccf"
    const val KEY = "api_key"

    const val API_READ_ACCESS_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDBkZmU4NWUwMjhlYTUyNDE1ZjEwMGMyZTQwOGNjZiIsInN1YiI6IjYwY2YzMmY5OWY1MWFmMDA1OGUyNzgyOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.B2wdWzNypxVGAUa9l0TYtc7zl8JPn2m_wfbqUb6G2tI"

    const val POPULAR = "/3/movie/popular"
    const val DISCOVER_MOVIE = "/3/discover/movie"
    const val SEARCH = "/3/search/movie"
    const val SEARCH_DEBOUNCE = 200L

    const val MOVIE_CREDITS ="/3/movie/{movie_id}/credits"
}