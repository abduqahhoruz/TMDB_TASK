package uz.instat.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEntity::class], version = 3, exportSchema = false)

abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun dao(): FavouriteDao
}