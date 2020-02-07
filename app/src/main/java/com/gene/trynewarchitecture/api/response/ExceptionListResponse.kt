package com.gene.trynewarchitecture.api.response

import com.google.gson.annotations.SerializedName


data class ExceptionListResponse (
        val exceptions: List<Exception> = emptyList()
) {
        data class Exception (

                val id: String = "",

                val code: String = "",

                val name: String = "",

                val type: String = "",

                @SerializedName("created_at")
                val createAt: String = "",

                @SerializedName("updated_at")
                val updatedAt: String = ""
        )
}