package com.gene.trynewarchitecture.api.response

import com.google.gson.annotations.SerializedName

data class BaseResponseV2 (

    val result: String = "",

    @SerializedName("error_message")
    val errorMessage: String = ""
)