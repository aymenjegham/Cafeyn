package com.aymen.cafeyn.data.dataSource.database


import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.database.PhotoDao
import javax.inject.Inject

class PhotoDatabaseImpl @Inject constructor(
    private val photoDao: PhotoDao,
) : PhotoDatabase {
    override fun getAll(): List<PhotoItem>? = photoDao.getAll()

    override fun insertAll(photos: List<PhotoItem>) = photoDao.insertAll(photos)

    override fun deleteAll(photos: List<PhotoItem>) = photoDao.deleteAll(photos)


}