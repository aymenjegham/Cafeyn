package com.aymen.cafeyn.di.module

import android.content.Context
import androidx.room.Room
import com.aymen.cafeyn.database.DATABASE_NAME
import com.aymen.cafeyn.database.Database
import com.aymen.cafeyn.database.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Module
    class DatabaseModule {
        @Provides
        fun provideDao(database: Database): PhotoDao {
            return database.photoDao()
        }
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            DATABASE_NAME
        ).build()
    }

}