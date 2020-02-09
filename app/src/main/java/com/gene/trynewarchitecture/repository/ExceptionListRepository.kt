package com.gene.trynewarchitecture.repository

import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.api.request.ExceptionListRequest
import com.gene.trynewarchitecture.api.response.ExceptionListResponse
import com.gene.trynewarchitecture.api.response.ExceptionListResponse.Exception
import com.gene.trynewarchitecture.room.ExceptionListDao
import com.gene.trynewarchitecture.room.entity.ExceptionList
import com.gene.trynewarchitecture.utils.Resource

class ExceptionListRepository(
    private val networkService: NetworkService,
    private val exceptionListDao: ExceptionListDao
) {


    suspend fun getExceptionList(type: String): Resource<List<Exception>> {
        return object : NetworkBoundFlow<List<Exception>,ExceptionListResponse>() {
            override suspend fun saveCallResult(item: ExceptionListResponse) {
                exceptionListDao.insert(ExceptionList(type, item.exceptions))
            }

            override suspend fun createCall(): ExceptionListResponse {
                return networkService.getExceptionList(ExceptionListRequest(type))
            }

            override fun shouldFetch(data: List<Exception>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun loadFromDb(): List<Exception>? {
                val el = exceptionListDao.getExceptionList(type)
                return el?.list
            }
        }.run()
    }
}
