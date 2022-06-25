package com.aymen.cafeyn.data.useCase.getAllPhotos


import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.data.repository.photo.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPhotosUseCaseImpl @Inject constructor(private val photoRepository: PhotoRepository) :
    GetAllPhotosUseCase {

    override fun invoke(): Flow<Result<List<PhotoItem>>?> = photoRepository.getAllPhotos()

}