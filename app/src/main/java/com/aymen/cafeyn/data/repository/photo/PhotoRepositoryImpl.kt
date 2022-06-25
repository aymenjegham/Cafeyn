package com.aymen.cafeyn.data.repository.photo

import android.content.Context
import com.aymen.cafeyn.data.dataSource.api.APIClient
import com.aymen.cafeyn.data.dataSource.database.PhotoDatabase
import com.aymen.cafeyn.data.model.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.aymen.cafeyn.data.model.Result
import com.aymen.cafeyn.global.extension.isNetworkAvailable
import com.aymen.cafeyn.global.utils.ErrorUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import retrofit2.Retrofit


class PhotoRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val photoDatabase: PhotoDatabase,
    private val retrofit: Retrofit,
    @ApplicationContext private val context: Context,
) :
    PhotoRepository {
    override fun getAllPhotos(): Flow<Result<List<PhotoItem>>?> {
        return flow {
            emit(fetchCachedPhotos())
            emit(Result.loading())
            val result = getResponse(
                request = { api.getAllPhotos() },
                defaultErrorMessage = "Error fetching photos list"
            )

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.let { it ->
                    photoDatabase.insertAll(it)
                }
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchCachedPhotos(): Result<List<PhotoItem>>? =
        photoDatabase.getAll()?.let {
            Result.success(it)
        }

    private suspend fun getResponse(
        request: suspend () -> Response<List<PhotoItem>>,
        defaultErrorMessage: String
    ): Result<List<PhotoItem>> {
        return try {
            val result = request()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (context.isNetworkAvailable()) {
                Result.error("Unknown Error ", null)
            } else {
                Result.error("No network available", null)
            }

        }
    }


}