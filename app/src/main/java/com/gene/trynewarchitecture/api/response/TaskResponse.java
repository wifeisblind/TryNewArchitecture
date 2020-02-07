package com.gene.trynewarchitecture.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by arvin on 2018/2/14.
 */

@SuppressWarnings("DefaultFileTemplate")
public class TaskResponse {
    @SuppressWarnings("unused")
    @SerializedName("tasks")
    private ArrayList<TaskListResponse> getTasks;

    @SuppressWarnings("unused")
    @SerializedName("order_counts")
    private int orderCount;

    @SuppressWarnings("unused")
    @SerializedName("ship_counts")
    private int shipCount;

    @SuppressWarnings("unused")
    @SerializedName("sys_time")
    private long sysTime;

    public ArrayList<TaskListResponse> getGetTasks() {
        return getTasks;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getShipCount() {
        return shipCount;
    }

    public long getSysTime() {
        return sysTime;
    }
}
