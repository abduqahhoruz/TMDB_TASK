package uz.instat.tmdb.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.instat.tmdb.data.network.MoviesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val PREF_NAME = "tmdb_task_prefs"

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferenceEditor(
        prefs: SharedPreferences
    ): SharedPreferences.Editor {
        return prefs.edit()
    }

    @Singleton
    @Provides
    fun providePopularApi(
        retrofit: Retrofit
    ): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }
}