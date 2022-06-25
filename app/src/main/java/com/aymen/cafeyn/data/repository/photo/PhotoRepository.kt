package com.aymen.cafeyn.data.repository.photo

import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    fun getAllPhotos(): Flow<Result<List<PhotoItem>>?>

}