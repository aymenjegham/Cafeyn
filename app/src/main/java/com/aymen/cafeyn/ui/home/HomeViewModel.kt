package com.aymen.cafeyn.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.data.useCase.getAllPhotos.GetAllPhotosUseCase
import com.aymen.cafeyn.global.helper.DebugLog
import com.aymen.cafeyn.global.helper.Navigation
import com.aymen.cafeyn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val getAllPhotosUseCase: GetAllPhotosUseCase
) : BaseViewModel
    (app) {

    private val _resultPhotoList = MutableLiveData<Result<List<PhotoItem>>>()
    val resultPhotoList = _resultPhotoList


    init {
        fetchPhotos()
    }

    fun fetchPhotos() {

        viewModelScope.launch {
            getAllPhotosUseCase().collect {
                _resultPhotoList.value = it
            }
        }
    }

    fun navigate(): (Int) -> Unit = {
        _resultPhotoList.value?.data?.get(it)?.let { photoItem ->
            navigate(Navigation.NavigationDetails(photoItem))
        }
    }
}