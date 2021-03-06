package com.aymen.cafeyn.di.module

import android.content.Context
import com.aymen.cafeyn.data.dataSource.api.EndpointInterceptor
import com.aymen.cafeyn.di.qualifier.PicassoHttpClient
import com.aymen.cafeyn.global.constants.CONNECT_TIMEOUT
import com.aymen.cafeyn.global.constants.OKHTTP_CACHE_FILE_NAME
import com.aymen.cafeyn.global.constants.READ_TIMEOUT
import com.aymen.cafeyn.global.constants.WRITE_TIMEOUT
import com.aymen.cafeyn.global.helper.DebugLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        DebugLog.d(
            "cafeyn_log",
            message
        )
    }.run {
        level = HttpLoggingInterceptor.Level.BODY
        this
    }

    @Provides
    @Singleton
    fun providesOkHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        endpointInterceptor: EndpointInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(endpointInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesCacheFile(@ApplicationContext context: Context): File =
        File(context.cacheDir, OKHTTP_CACHE_FILE_NAME)


    @Provides
    @Singleton
    fun providesCache(cacheFile: File): Cache = Cache(cacheFile, 10L * 1000 * 1000)//10MB Cache


    @Provides
    @Singleton
    @PicassoHttpClient
    fun providesPicassoOkHTTPClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: EndpointInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .cache(cache)
            .build()

}