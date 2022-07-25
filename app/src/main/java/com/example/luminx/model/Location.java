package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

public class Location{
    @SerializedName("current_location")
    public String current_location;
    @SerializedName("street_name")
    public String street_name;
    @SerializedName("zip_code")
    public String zip_code;

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}