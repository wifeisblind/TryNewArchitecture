package com.gene.trynewarchitecture.api.request

import com.google.gson.annotations.SerializedName

data class TaskListParameter (

        @SerializedName("task_id")
        var taskId: String = "",

        @SerializedName("is_asc")
        var isAsc: Boolean = true,

        @SerializedName("task_type")
        var taskType: String = "0",

        @SerializedName("is_order")
        var isOrder: Boolean = true,

        @SerializedName("order_by")
        var orderBy: Int = 1
)