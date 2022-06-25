package com.aymen.cafeyn.data.dataSource.database


import com.aymen.cafeyn.data.model.PhotoItem


interface PhotoDatabase {

    fun getAll(): List<PhotoItem>?

    fun insertAll(photos: List<PhotoItem>)

    fun deleteAll(photos: List<PhotoItem>)

}