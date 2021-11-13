package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "created")
    private Timestamp created;

    @CreationTimestamp
    @Column(name = "modified")
    private Timestamp modified;

    @SerializedName("last_login")
    @CreationTimestamp
    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "token", columnDefinition = "varchar(256)")
    private String token;

    @SerializedName("isactive")
    @Column(name = "isactive")
    private boolean isActive;

    @OneToMany(mappedBy="user")
    private List<UserPhone> phones;

    public User(String name, String email, String password, List<UserPhone>phones, boolean isActive) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<UserPhone> phones) {
        this.phones = phones;
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

    @Override
    public String toString() {
        return "UserName: '" + this.name + "', Email: '" + this.email;
    }

    public boolean isNullField() {
        return Stream
                .of(name, email, password)
                .anyMatch(Objects::isNull);
    }
}
