package com.gene.trynewarchitecture.repository

import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.api.request.ExceptionListRequest
import com.gene.trynewarchitecture.api.response.ExceptionListResponse.Exception

class ExceptionListRepository(private val networkService: NetworkService) {


    suspend fun getExceptionList(type: String): List<Exception> {
        return networkService.getExceptionList(ExceptionListRequest(type)).exceptions
    }
}
