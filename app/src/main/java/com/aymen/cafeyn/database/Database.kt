package com.aymen.cafeyn.database


import androidx.room.RoomDatabase
import com.aymen.cafeyn.data.model.PhotoItem
import com.aymen.cafeyn.global.constants.DATABASE_FILE_NAME
import androidx.room.Database as RoomDatabse


const val DATABASE_NAME = DATABASE_FILE_NAME
const val DATABASE_VERSION = 1

@RoomDatabse(
    entities = [
        PhotoItem::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)


abstract class Database : RoomDatabase() {

    abstract fun photoDao(): PhotoDao


}