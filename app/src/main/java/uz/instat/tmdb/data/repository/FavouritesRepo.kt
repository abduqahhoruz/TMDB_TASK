package uz.instat.tmdb.data.repository

import kotlinx.coroutines.flow.Flow
import uz.instat.tmdb.data.local.FavouriteEntity

interface FavouritesRepo {
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity)
    suspend fun getAllFavourites(): Flow<List<FavouriteEntity>>
    suspend fun checkIfFavourite(id: Long): Flow<Boolean>
    suspend fun deleteFavourite(id: Long): Int
}
