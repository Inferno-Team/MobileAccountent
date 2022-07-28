package com.inferno.mobile.accountent.models;

public class Worker {

    private final PositionType position;
    private final int shopId;
    private final User worker;

    public Worker(PositionType position, int shopId,User worker) {
        this.position = position;
        this.shopId = shopId;
        this.worker = worker;
    }

    public User getWorker() {
        return worker;
    }

    public PositionType getPosition() {
        return position;
    }

    public int getShopId() {
        return shopId;
    }

}
