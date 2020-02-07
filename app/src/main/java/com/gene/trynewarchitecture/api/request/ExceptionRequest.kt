package com.gene.trynewarchitecture.api.request

import com.google.gson.annotations.SerializedName

data class ExceptionRequest (
    @SerializedName("task_id")
    val taskId: String = "",

    @SerializedName("exception_id")
    val exceptionId: String = "",

    val memo: String = ""
)