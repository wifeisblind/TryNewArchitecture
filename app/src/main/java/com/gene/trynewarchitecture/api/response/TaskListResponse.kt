package com.gene.trynewarchitecture.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by arvin on 2018/2/14.
 */
data class TaskListResponse (

    @SerializedName("task_id")
    val taskId: String? = "",

    @SerializedName("task_no")
    val taskNo: String? = "",

    @SerializedName("name")
    val userName: String? = "",

    @SerializedName("phone")
    val fromPhone: String? = "",

    @SerializedName("get_phone")
    val toPhone: String? = "",

    @SerializedName("address")
    val pickUpAddress: String? = "",

    @SerializedName("put_address")
    var deliveryAddress: String? = "",

    @SerializedName("extendsString")
    val additionalItems: String? = "",

    @SerializedName("cod")
    val cashOnDelivery: String? = "",

    @SerializedName("put_name")
    val recipient: String? = "",

    @SerializedName("is_order")
    val isOrder: Boolean = false,

    @SerializedName("driverpool_id")
    val driverPoolId: Int = 0,

    @SerializedName("driver_id")
    val driverId: Int = 0,

    @SerializedName("memo")
    val memoContent: String? = "",

    @SerializedName("status")
    val taskStatus: Int = 0,

    @SerializedName("updated_at")
    var updateAt: String? = "",

    @SerializedName("created_at")
    val createAt: String? = "",

    @SerializedName("status_name")
    val statusName: String? = "",

    @SerializedName("exceptions")
    val exceptionCodeList: List<Exception>? = emptyList(),

    @SerializedName("is_direct_change_status")
    val isDirectChangeStatus: Int = 0,

    val deadline: String? = "",

    @SerializedName("return_status")
    val returnStatus: Int? = 0,

    val type: String = "DR"

)