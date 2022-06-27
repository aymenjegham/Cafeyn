package com.aymen.cafeyn.ui.home



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.data.repository.FakePhotoListRepository
import com.aymen.cafeyn.data.repository.photo.PhotoRepository
import com.aymen.cafeyn.data.useCase.getAllPhotos.GetAllPhotosUseCaseImpl
import com.aymen.cafeyn.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private lateinit var getAllPhotosUseCase: GetAllPhotosUseCaseImpl
    private lateinit var photoRepository: PhotoRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
     fun `fetching_photos_returns_successfully`(){

        photoRepository = FakePhotoListRepository(false)
        getAllPhotosUseCase = GetAllPhotosUseCaseImpl(photoRepository)

        viewModel = HomeViewModel(
            ApplicationProvider.getApplicationContext(),
            getAllPhotosUseCase
        )

        viewModel.fetchPhotos()

        val value = viewModel.resultPhotoList.getOrAwaitValue()

            assertThat(value?.status).isEqualTo(Result.Status.SUCCESS)


    }

    @Test
    fun `fetching_photos_returns_Error`(){

        photoRepository = FakePhotoListRepository(true)
        getAllPhotosUseCase = GetAllPhotosUseCaseImpl(photoRepository)

        viewModel = HomeViewModel(
            ApplicationProvider.getApplicationContext(),
            getAllPhotosUseCase
        )

        viewModel.fetchPhotos()

        val value = viewModel.resultPhotoList.getOrAwaitValue()

        assertThat(value?.status).isEqualTo(Result.Status.ERROR)


    }
}