package com.gene.trynewarchitecture.viewmodel

import androidx.lifecycle.*
import com.gene.trynewarchitecture.api.response.TaskListResponse
import com.gene.trynewarchitecture.repository.ExceptionListRepository
import com.gene.trynewarchitecture.repository.TasksRepository
import com.gene.trynewarchitecture.utils.Status
import com.gene.trynewarchitecture.utils.Status.*

@Suppress("CanBeParameter")
class ExceptionViewModel(
    private val tasksRepository: TasksRepository,
    private val exceptionListRepository: ExceptionListRepository
    ) : ViewModel() {

    private var _taskNo: String? = null

    val status: MutableLiveData<Status> = MutableLiveData()

    private val taskResponse: LiveData<TaskListResponse> by lazy {
        liveData {
            val resource = tasksRepository.getTaskByNumber(_taskNo!!)
            if (resource.isSuccess) {
                emit(resource.data!!)
            } else {
                status.value = ERROR
            }
        }
    }

    val exceptionList: LiveData<List<String>> by lazy {
        status.value = LOADING
        taskResponse.switchMap { task ->
            liveData {
                val resource = exceptionListRepository.getExceptionList(task.type)
                if (resource.isSuccess) {
                    emit(resource.data!!.map { it.name })
                    status.value = SUCCESS
                } else {
                    status.value = ERROR
                }
            }
        }
    }

    fun setTaskNo(taskNo: String) {
        _taskNo = taskNo
    }
}
