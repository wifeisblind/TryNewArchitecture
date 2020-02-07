package com.gene.trynewarchitecture.room.entity

import androidx.room.Entity


@Entity(
        tableName = "task_table",
        primaryKeys = ["taskNo"]
)
data class Task (
        val taskNo: String = "",
        val recipient: String = "",
        val deliveryAddress: String = "",
        val statusName: String = ""
)