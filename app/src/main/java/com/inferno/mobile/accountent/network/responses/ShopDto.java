package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class ShopDto {

    @SerializedName("id")
    private final int id;
    @SerializedName("owner_id")
    private final int ownerId;
    @SerializedName("admin_id")
    private final int adminId;
    @SerializedName("shope_id")
    private final int shopeId;
    @SerializedName("name")
    private final String name;
    @SerializedName("location")
    private final String location;
    @SerializedName("approved")
    private final boolean approved;
    @SerializedName("has_manager")
    private final boolean hasManager;

    @SerializedName("request_type")
    private final String requestType;
    @SerializedName("created_at")
    private final Date created_at;
    @SerializedName("updated_at")
    private final Date updated_at;
    @SerializedName("admin")
    private final UserDto admin;
    @SerializedName("owner")
    private final UserDto owner;
    @SerializedName("shope_staff")
    private final ArrayList<WorkerDto>workers;

    public int getId() {
        return id;
    }

    public ShopDto(int id, int ownerId, int adminId, int shopeId, String name, String location,
                   boolean approved, boolean hasManager, String requestType, Date created_at,
                   Date updated_at, UserDto admin, UserDto owner,
                   ArrayList<WorkerDto> dtos) {
        this.id = id;
        this.ownerId = ownerId;
        this.adminId = adminId;
        this.shopeId = shopeId;
        this.name = name;
        this.location = location;
        this.approved = approved;
        this.hasManager = hasManager;
        this.requestType = requestType;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.admin = admin;
        this.owner = owner;
        this.workers = dtos;
    }

    public ArrayList<WorkerDto> getWorkers() {
        return workers;
    }

    public boolean isHasManager() {
        return hasManager;
    }

    public UserDto getAdmin() {
        return admin;
    }

    public UserDto getOwner() {
        return owner;
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

    public String getName() {
        return name;
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
