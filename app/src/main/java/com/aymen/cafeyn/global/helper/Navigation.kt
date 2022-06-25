package com.aymen.cafeyn.global.helper

import com.aymen.cafeyn.data.model.PhotoItem


sealed class Navigation {


    data class Back(val ShouldFinish: Boolean) : Navigation()

    data class NavigationDetails(val photoItem: PhotoItem) : Navigation()


}