package com.aymen.cafeyn.ui.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.global.helper.DebugLog
import com.aymen.cafeyn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    app: Application,
) : BaseViewModel
    (
    app
) {

    private val _photoItem = MutableLiveData<PhotoItem>()
    val photoItem: LiveData<PhotoItem>
        get() = _photoItem

    fun setPhotoItem(photoItem: PhotoItem) {
        DebugLog.v("checkingResult",photoItem.toString())
        _photoItem.postValue(photoItem)
    }


}