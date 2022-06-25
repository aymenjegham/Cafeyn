package com.aymen.cafeyn.data.dataSource.api

import com.aymen.cafeyn.data.model.PhotoItem
import retrofit2.Response
import retrofit2.http.GET


interface APIClient {

    @GET("photos")
    suspend fun getAllPhotos(): Response<List<PhotoItem>>


}