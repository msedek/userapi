package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateUserResponse {

    private UUID id;
    private Timestamp created;
    private Timestamp modified;
    @SerializedName("last_login")
    private Timestamp lastLogin;
    private String token;
    @SerializedName("isactive")
    private boolean isActive;
}
