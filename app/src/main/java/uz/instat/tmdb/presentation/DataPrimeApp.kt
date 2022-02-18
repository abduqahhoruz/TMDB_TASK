package uz.instat.tmdb.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.instat.tmdb.BuildConfig

@HiltAndroidApp
class DataPrimeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}