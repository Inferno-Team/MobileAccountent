package com.inferno.mobile.accountent.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Shop implements Serializable {
    private final int id;
    private final int ownerId;
    private final int adminId;
    private final int shopeId;

    private final boolean hasManager;

    private final String shopName;
    private final String location;
    private boolean approved;
    private final String requestType;
    private final Date created_at;
    private final Date updated_at;
    private final User admin;
    private final User owner;
    private final ArrayList<Worker>workers;


    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getAdmin() {
        return admin;
    }

    public User getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public Shop(int id, int ownerId, int adminId, int shopeId, boolean hasManager,
                String shopName, String location,
                boolean approved, String requestType, Date created_at,
                Date updated_at, User admin, User owner, ArrayList<Worker> workers) {
        this.id = id;
        this.ownerId = ownerId;
        this.adminId = adminId;
        this.shopeId = shopeId;
        this.hasManager = hasManager;
        this.shopName = shopName;
        this.location = location;
        this.approved = approved;
        this.requestType = requestType;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.admin = admin;
        this.owner = owner;
        this.workers = workers;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public boolean isHasManager() {
        return hasManager;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getAdminId() {
        return adminId;
    }

    public int getShopeId() {
        return shopeId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getLocation() {
        return location;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getRequestType() {
        return requestType;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}
