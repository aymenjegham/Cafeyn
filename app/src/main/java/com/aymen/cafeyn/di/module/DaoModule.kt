package com.aymen.cafeyn.di.module



import com.aymen.cafeyn.database.Database
import com.aymen.cafeyn.database.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {


    @Provides
    fun provideConfereeDao(database: Database): PhotoDao {
        return database.photoDao()
    }


}