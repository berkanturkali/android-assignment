package com.arabam.android.assignment.core.data.util

import com.arabam.android.assignment.core.model.Resource.Error
import com.arabam.android.assignment.core.model.Resource.Success
import com.arabam.android.assignment.core.network.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.core.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

public suspend fun <T, D> safeApiCall(
    mapper: RemoteModelMapper<T, D>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): Resource<D> {
    return try {
        val response = withContext(dispatcher) { apiCall.invoke() }
        if (response.isSuccessful) {
            response.body()
                ?.let {
                    Success(
                        mapper.mapFromModel(it)
                    )
                } ?: Error("null")
        } else {
            Error(
                response.errorBody()
                    ?.string() ?: ""
            )
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is TimeoutCancellationException -> {
                Error("Timeout")
            }
            is IOException -> {
                Error(
                    throwable.localizedMessage ?: ""
                )
            }
            is HttpException -> {
                val errorResponse = convertErrorBody(throwable)
                Error(errorResponse!!)
            }
            else -> {
                Error("Unknown Error")
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()
            ?.errorBody()
            ?.string()
    } catch (exception: Exception) {
        "Unknown Error"
    }
}
