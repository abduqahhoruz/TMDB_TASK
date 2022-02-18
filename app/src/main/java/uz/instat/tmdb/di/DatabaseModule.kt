package uz.instat.tmdb.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.instat.tmdb.data.local.FavouriteDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): FavouriteDatabase {
        return Room.databaseBuilder(context, FavouriteDatabase::class.java, "favourites")
            .fallbackToDestructiveMigration()
            .build()
    }
}

