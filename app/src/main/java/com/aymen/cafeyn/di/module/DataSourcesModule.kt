package com.aymen.cafeyn.di.module


import com.aymen.cafeyn.data.dataSource.database.PhotoDatabase
import com.aymen.cafeyn.data.dataSource.database.PhotoDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsPhotoDatabase(photoDatabaseImpl: PhotoDatabaseImpl): PhotoDatabase


}