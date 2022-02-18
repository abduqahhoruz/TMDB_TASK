package uz.instat.tmdb.data.local.datasource.abstract

import kotlinx.coroutines.flow.Flow
import uz.instat.tmdb.data.local.FavouriteEntity

interface FavouriteMovieDataSource {
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity): Long

    suspend fun deleteFavourite(id: Long): Int

    suspend fun checkIfFavourite(id: Long): Boolean

    suspend fun getAllFavourites(): Flow<MutableList<FavouriteEntity>>
}