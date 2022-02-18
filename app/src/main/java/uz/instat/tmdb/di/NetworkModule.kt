package uz.instat.tmdb.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.instat.tmdb.data.network.NetworkConstants
import uz.instat.tmdb.data.network.NetworkConstants.API_KEY
import uz.instat.tmdb.data.network.NetworkConstants.BASE_URL
import uz.instat.tmdb.data.network.NetworkConstants.KEY
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesInterceptionToRetrofit(): Interceptor {
        return Interceptor { chain ->
            val interceptedUrl = chain
                .request()
                .url
                .newBuilder()
                .addQueryParameter(
                    KEY,
                    API_KEY
                ).build()

            val request = chain
                .request()
                .newBuilder()
                .url(interceptedUrl)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(NetworkConstants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(NetworkConstants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .build()
                it.proceed(request)
            }
        okHttpClient.addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        ).addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(android.R.color.holo_green_dark)
            .error(android.R.color.holo_red_dark)
    }

    @Singleton
    @Provides
    fun providesGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

}