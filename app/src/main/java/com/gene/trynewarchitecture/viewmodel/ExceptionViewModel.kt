package com.gene.trynewarchitecture.viewmodel

import androidx.lifecycle.*
import com.gene.trynewarchitecture.api.response.TaskListResponse
import com.gene.trynewarchitecture.repository.ExceptionListRepository
import com.gene.trynewarchitecture.repository.TasksRepository

@Suppress("CanBeParameter")
class ExceptionViewModel(
    private val tasksRepository: TasksRepository,
    private val exceptionListRepository: ExceptionListRepository
    ) : ViewModel() {

    private var _taskNo: String? = null

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val taskResponse: LiveData<TaskListResponse> by lazy {
        liveData {
            emit(tasksRepository.getTaskByNumber(_taskNo!!))
        }
    }

    val exceptionList: LiveData<List<String>> by lazy {
        isLoading.value = true
        taskResponse.switchMap { task ->
            liveData {
                emit(exceptionListRepository.getExceptionList(task.type).map { it.name })
                isLoading.value = false
            }
        }
    }

    fun setTaskNo(taskNo: String) {
        _taskNo = taskNo
    }
}
