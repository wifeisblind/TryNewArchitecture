package com.gene.trynewarchitecture.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gene.trynewarchitecture.api.response.ExceptionListResponse.Exception
import com.gene.trynewarchitecture.room.ExceptionListTypeConverter

@Entity(tableName = "exception_list")
@TypeConverters(ExceptionListTypeConverter::class)
data class ExceptionList (

        @PrimaryKey
        val type: String,

        val list: List<Exception>
)