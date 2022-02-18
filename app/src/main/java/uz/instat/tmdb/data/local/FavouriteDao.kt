package uz.instat.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity): Long

    @Query("select * from favourites")
    fun getAllFavourites(): Flow<MutableList<FavouriteEntity>>

    @Query("""select * from favourites where _id=:id""")
    fun checkIfFavourite(id: Long): Boolean

    @Query("""DELETE FROM favourites WHERE _id =:id""")
    fun deleteFavourite(id: Long): Int

}