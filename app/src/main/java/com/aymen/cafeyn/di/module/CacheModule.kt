package com.aymen.cafeyn.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.datastore.preferences.createDataStore
import com.aymen.cafeyn.data.cache.Cache
import com.aymen.cafeyn.data.cache.CacheImpl
import com.aymen.cafeyn.global.constants.CACHE_FILE_NAME


@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) =
        context.createDataStore(CACHE_FILE_NAME)

    @Singleton
    @Provides
    fun provideCache(dataStore: DataStore<Preferences>, gson: Gson): Cache = CacheImpl(dataStore, gson)

}