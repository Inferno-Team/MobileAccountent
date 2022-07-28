package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class WorkerDto {
    @SerializedName("postion")
    private final String position;
    @SerializedName("shope_id")
    private final int shopId;

    @SerializedName("worker")
    private final UserDto worker;

    public WorkerDto(String position, int shopId,UserDto worker) {
        this.position = position;
        this.shopId = shopId;
        this.worker = worker;
    }

    public UserDto getWorker() {
        return worker;
    }

    public String getPosition() {
        return position;
    }

    public int getShopId() {
        return shopId;
    }


}
