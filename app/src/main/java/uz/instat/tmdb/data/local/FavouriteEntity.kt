package uz.instat.tmdb.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: Long = 0L,

    @ColumnInfo(name = "_name")
    val name: String = "",

    @ColumnInfo(name = "_image_url")
    val image_url: String = "",

    @ColumnInfo(name = "_release_date")
    val release_date : String = "",

    @ColumnInfo(name = "_vote_average")
    val vote_average : Double = 0.0
)