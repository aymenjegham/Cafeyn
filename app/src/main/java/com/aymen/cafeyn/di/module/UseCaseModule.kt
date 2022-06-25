package com.aymen.cafeyn.di.module


import com.aymen.cafeyn.data.useCase.getAllPhotos.GetAllPhotosUseCase
import com.aymen.cafeyn.data.useCase.getAllPhotos.GetAllPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    abstract fun bindGetAllPhotosUseCase(useCase: GetAllPhotosUseCaseImpl): GetAllPhotosUseCase

}
