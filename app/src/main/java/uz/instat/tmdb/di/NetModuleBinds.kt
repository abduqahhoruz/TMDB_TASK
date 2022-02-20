package uz.instat.tmdb.di

import androidx.room.Insert
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.instat.tmdb.data.network.MovieDataSource
import uz.instat.tmdb.data.network.MovieDataSourceImpl
import uz.instat.tmdb.data.repository.MoviesRepo
import uz.instat.tmdb.data.repository.MoviesRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetModuleBinds {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(moviesRepoImpl: MoviesRepoImpl) : MoviesRepo

    @Singleton
    @Binds
    abstract fun bindMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}