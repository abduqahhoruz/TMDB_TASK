package uz.instat.tmdb.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.instat.tmdb.data.local.FavouriteDatabase
import uz.instat.tmdb.data.local.FavouriteEntity
import javax.inject.Inject

class FavouritesRepoImpl
@Inject constructor(database: FavouriteDatabase) :
    FavouritesRepo {
    private val favouriteDao = database.dao()
    override suspend fun insertFavourite(favouriteEntity: FavouriteEntity){
        return favouriteDao.insertFavourite(favouriteEntity)
    }

    override suspend fun getAllFavourites(): Flow<List<FavouriteEntity>> {
        return flow {
            emit(favouriteDao.getAllFavourites())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun checkIfFavourite(id: Long): Flow<Boolean> {
        return flow {
            emit(favouriteDao.checkIfFavourite(id))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun deleteFavourite(id: Long): Int {
        return favouriteDao.deleteFavourite(id)
    }
}