package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;
import javax.persistence.*;

@Entity
@Table(name = "PHONES")
public class UserPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, updatable = false)
    private User user;

    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    @SerializedName("citycode")
    private String cityCode;

    @Column(name = "country_code")
    @SerializedName("contrycode")
    private String countryCode;

    public UserPhone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Phone: '" + this.number + "', CityCode: '" + this.cityCode+ "', CountryCode: '" + this.countryCode;
    }
}
