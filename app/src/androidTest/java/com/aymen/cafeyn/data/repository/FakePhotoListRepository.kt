package com.aymen.cafeyn.data.repository

import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.data.repository.photo.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakePhotoListRepository(
    private val errorValue: Boolean
) : PhotoRepository {

    private var shouldReturnNetworkError = false


    private val fakeListOfPhotoItems = listOf(
        PhotoItem(
            id = 1, albumId = 1,
            thumbnailUrl = "https://via.placeholder.com/150/92c952",
            title = "accusamus beatae ad facilis cum similique qui sunt",
            url = "https://via.placeholder.com/600/92c952"
        ),
        PhotoItem(
            id = 2, albumId = 1,
            thumbnailUrl = "https://via.placeholder.com/150/771796",
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796"
        )
    )
    private val response = Result(Result.Status.SUCCESS, fakeListOfPhotoItems, null, null)
    private val responseFailure = Result(Result.Status.ERROR, fakeListOfPhotoItems, null, null)


    override fun getAllPhotos(): Flow<Result<List<PhotoItem>>?> {
        return flow {
            if (errorValue) {
                emit(responseFailure)
            } else {
                emit(response)
            }
        }
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }
}