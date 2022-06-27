package com.aymen.cafeyn.data.repository.photo

import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakePhotoListRepository : PhotoRepository {

    private val fakeListOfPhotoItems = listOf(
        PhotoItem(id = 1, albumId = 1,
            thumbnailUrl = "https://via.placeholder.com/150/92c952",
            title = "accusamus beatae ad facilis cum similique qui sunt",
            url = "https://via.placeholder.com/600/92c952"
        ),
        PhotoItem(id = 2, albumId = 1,
            thumbnailUrl = "https://via.placeholder.com/150/771796",
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796"
        )
    )
    private val response = Result(Result.Status.SUCCESS, fakeListOfPhotoItems,null,null)


    override fun getAllPhotos(): Flow<Result<List<PhotoItem>>?> {
        return flow { emit(response) }

    }
}