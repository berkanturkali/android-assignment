package com.arabam.android.assignment.util

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T, D> safeApiCall(
    mapper: RemoteModelMapper<T, D>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): Resource<D> {
    return try {
        val response = withContext(dispatcher) { apiCall.invoke() }
        if (response.isSuccessful) {
            response.body()?.let {
                Resource.Success(mapper.mapFromModel(it))
            } ?: Resource.Error("null")
        } else {
            Resource.Error(response.errorBody()?.string() ?: "")
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is TimeoutCancellationException -> {
                Resource.Error("Timeout")
            }
            is IOException -> {
                Resource.Error(throwable.localizedMessage ?: "")
            }
            is HttpException -> {
                val errorResponse = convertErrorBody(throwable)
                Resource.Error(errorResponse!!)
            }
            else -> {
                Resource.Error("Unknown Error")
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        "Unknown Error"
    }
}
