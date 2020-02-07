package com.gene.trynewarchitecture.api

import com.gene.trynewarchitecture.api.request.ExceptionListRequest
import com.gene.trynewarchitecture.api.request.ExceptionRequest
import com.gene.trynewarchitecture.api.request.TaskListParameter
import com.gene.trynewarchitecture.api.response.ExceptionListResponse
import com.gene.trynewarchitecture.api.response.TaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    @POST("api/get_exception_list")
    suspend fun getExceptionList(@Body request: ExceptionListRequest): Response<ExceptionListResponse>

    @POST("api/set_exception")
    suspend fun setExcepton(@Body request: ExceptionRequest): Response<Int>

    @POST("api/get_tasklist")
    suspend fun getExceptionList(@Body parameter: TaskListParameter): Response<TaskResponse>
}