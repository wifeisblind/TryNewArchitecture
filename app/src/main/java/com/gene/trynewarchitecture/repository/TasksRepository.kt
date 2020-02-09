package com.gene.trynewarchitecture.repository

import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.api.request.TaskListParameter
import com.gene.trynewarchitecture.api.response.TaskListResponse
import com.gene.trynewarchitecture.api.response.TaskResponse
import com.gene.trynewarchitecture.utils.Resource
import kotlinx.coroutines.delay

class TasksRepository(private val networkService: NetworkService) {


    suspend fun getTaskByNumber(taskNo: String): Resource<TaskListResponse> {
        val response = networkService.getTaskList(TaskListParameter()).getTasks.find { it.taskNo == taskNo }!!
        return Resource.success(response)
    }

}