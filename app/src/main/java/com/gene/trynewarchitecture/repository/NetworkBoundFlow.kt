package com.gene.trynewarchitecture.repository

import com.gene.trynewarchitecture.utils.Resource

abstract class NetworkBoundFlow<ResultType, RequestType> {

    suspend fun run(): Resource<ResultType> {
        val data = loadFromDb()
        return if (shouldFetch(data)) {
            fetchFromNetwork()
        } else {
            Resource.success(data)
        }
    }

    private suspend fun fetchFromNetwork(): Resource<ResultType> {
        return try {
            val response = createCall()
            saveCallResult(response)
            val data = loadFromDb()
            Resource.success(data)
        } catch (t: Throwable) {
            Resource.error(t.message ?: "unknown error")
        }
    }

    protected abstract suspend fun saveCallResult(item: RequestType)

    protected abstract suspend fun createCall(): RequestType

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun loadFromDb(): ResultType?
}