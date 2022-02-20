package uz.instat.tmdb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.instat.tmdb.data.repository.FavouritesRepo
import uz.instat.tmdb.data.repository.FavouritesRepoImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModuleBinds {

    @Binds
    abstract fun provideFavouritesRepo(favouritesRepoImpl: FavouritesRepoImpl): FavouritesRepo
}