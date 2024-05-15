package fr.fdj.footballleague.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.fdj.footballleague.api.service.LeagueService
import fr.fdj.footballleague.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {
    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        @Named("logging") loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://www.thesportsdb.com/api/v1/json/50130162/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideLeagueService(
        retrofit: Retrofit
    ): LeagueService {
        return retrofit.create<LeagueService>()
    }
}