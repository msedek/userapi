package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.UUID;

public class CreateUserResponse {

    private UUID id;
    private Timestamp created;
    private Timestamp modified;
    @SerializedName("last_login")
    private Timestamp lastLogin;
    private String token;
    @SerializedName("isactive")
    private boolean isActive;

    public CreateUserResponse(UUID id, Timestamp created, Timestamp modified, Timestamp lastLogin, String token, boolean isActive) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
