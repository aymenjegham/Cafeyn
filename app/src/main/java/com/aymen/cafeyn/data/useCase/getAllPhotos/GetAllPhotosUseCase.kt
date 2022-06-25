package com.aymen.cafeyn.data.useCase.getAllPhotos

import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import kotlinx.coroutines.flow.Flow

interface GetAllPhotosUseCase {

    operator fun invoke(): Flow<Result<List<PhotoItem>>?>
}