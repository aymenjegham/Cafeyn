package com.aymen.cafeyn.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aymen.cafeyn.data.model.PhotoItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PhotoDaoTest {

    private lateinit var database: Database
    private lateinit var dao: PhotoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()

        dao = database.photoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAllPhoto() = runTest {

        val photoItem1 = PhotoItem(1, 1, "thumbUrl", "title", "url")
        val photoItem2 = PhotoItem(2, 1, "thumbUrl2", "title2", "url2")
        val photoList = listOf(photoItem1, photoItem2)

        dao.insertAll(photoList)

        val allPhotos = dao.getAll()

        assertThat(allPhotos).contains(photoItem1)
    }

    @Test
    fun deleteAllPhoto() = runTest {

        val photoItem1 = PhotoItem(1, 1, "thumbUrl", "title", "url")
        val photoItem2 = PhotoItem(2, 1, "thumbUrl2", "title2", "url2")
        val photoList = listOf(photoItem1, photoItem2)

        dao.insertAll(photoList)
        dao.deleteAll()


        val allPhotos = dao.getAll()

        assertThat(allPhotos).doesNotContain(photoItem1)
    }


}