package com.aymen.cafeyn.ui.main

import android.app.Application
import com.aymen.cafeyn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,

) : BaseViewModel(app) {


}