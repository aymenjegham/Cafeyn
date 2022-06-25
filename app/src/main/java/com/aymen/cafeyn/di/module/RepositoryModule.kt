package com.aymen.cafeyn.di.module

import com.aymen.cafeyn.data.repository.photo.PhotoRepository
import com.aymen.cafeyn.data.repository.photo.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Reusable
    fun bindPhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository



}