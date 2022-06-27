package com.aymen.cafeyn.data.useCase.getAllPhotos

import com.aymen.cafeyn.data.repository.photo.FakePhotoListRepository
import com.aymen.cafeyn.data.repository.photo.PhotoRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class GetAllPhotosUseCaseImplTest{

    private lateinit var getAllPhotosUseCase: GetAllPhotosUseCaseImpl
    private lateinit var photoRepository: PhotoRepository

    @Before
    fun setUp(){
        photoRepository = FakePhotoListRepository()
        getAllPhotosUseCase = GetAllPhotosUseCaseImpl(photoRepository)
    }

    @Test
    fun `Get Photos List, correct photos list return` (): Unit = runBlocking{
        val photos = getAllPhotosUseCase().first()
        assertEquals(photos?.data?.get(0)?.title, ("accusamus beatae ad facilis cum similique qui sunt"))
    }

    @Test
    fun `Get Photos List, incorrect Photos list return` (): Unit = runBlocking{
        val photos = getAllPhotosUseCase().first()
        assertNotEquals(photos?.data?.get(1)?.title, ("accusamus beatae ad facilis cum similique qui sunt"))
    }

}