package com.gene.trynewarchitecture.repository

import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.api.request.TaskListParameter
import com.gene.trynewarchitecture.api.response.TaskListResponse
import com.gene.trynewarchitecture.api.response.TaskResponse
import kotlinx.coroutines.delay

class TasksRepository(private val networkService: NetworkService) {

    suspend fun getTaskList(): List<TaskListResponse> {
        delay(1000)
        return networkService.getTaskList(TaskListParameter()).getTasks
    }

    suspend fun getTaskByNumber(taskNo: String): TaskListResponse {
        return networkService.getTaskList(TaskListParameter()).getTasks.find { it.taskNo == taskNo }!!
    }

}