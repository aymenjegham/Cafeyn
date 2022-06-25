package com.aymen.cafeyn.database

import androidx.room.*
import com.aymen.cafeyn.data.model.PhotoItem

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoItem)

    @Query("SELECT * FROM Photo order by id ASC")
    fun getAll(): List<PhotoItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos: List<PhotoItem>)

    @Delete
    fun deleteAll(photos: List<PhotoItem>)

}