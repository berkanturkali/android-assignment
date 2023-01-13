package com.arabam.android.assignment.core.model

public sealed class Resource<T>(
    public val data: T? = null,
    public val message: String? = null,
) {
    public class Success<T>(data: T? = null) : Resource<T>(data)

    public class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    public class Loading<T>(data: T? = null) : Resource<T>(data)
}
