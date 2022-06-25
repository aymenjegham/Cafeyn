package com.aymen.cafeyn.ui.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aymen.cafeyn.global.helper.Navigation
import com.aymen.cafeyn.global.helper.SingleEventLiveDataEvent
import com.aymen.cafeyn.ui.data.DialogAction

abstract class BaseViewModel(
    private val app: Application,
) : AndroidViewModel(app) {


    protected val context: Context
        get() = app.applicationContext

    private val _showProgressBar = SingleEventLiveDataEvent<DialogAction>()
    val showProgressBar: LiveData<DialogAction>
        get() = _showProgressBar

    private val _showToast: SingleEventLiveDataEvent<String> = SingleEventLiveDataEvent()
    val showToast: LiveData<String>
        get() = _showToast

    private val _showSnackBar = SingleEventLiveDataEvent<Pair<String,() -> Unit>>()
    val showSnackBar: LiveData<Pair<String,() -> Unit>>
        get() = _showSnackBar

    private val _navigation: SingleEventLiveDataEvent<Navigation> = SingleEventLiveDataEvent()
    val navigation: LiveData<Navigation>
        get() = _navigation

    fun showToast(message: String) {
        _showToast.value = message
    }

    fun showSnackBar(msg: String,action : () -> Unit) {
        _showSnackBar.value = Pair(msg,action)
    }

    fun showProgressBar() {
        _showProgressBar.value = DialogAction.SHOW
    }

    fun hideProgressBar() {
        _showProgressBar.value = DialogAction.DISMISS
    }

    fun navigate(navigationTo: Navigation) {
        _navigation.value = navigationTo
    }

    open fun navigateBack(shouldFinish: Boolean) {
        _navigation.value = Navigation.Back(shouldFinish)
    }

}