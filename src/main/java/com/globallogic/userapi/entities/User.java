package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity(name="User")
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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

    @OneToMany(targetEntity = UserPhone.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<UserPhone> phones;

    @Entity(name="UserPhone")
    @Table(name = "PHONES")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class UserPhone {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name = "number")
        private String number;

        @Column(name = "city_code")
        @SerializedName("citycode")
        private String cityCode;

        @Column(name = "country_code")
        @SerializedName("contrycode")
        private String countryCode;
    }
}
