package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

public class Root2 {

    @SerializedName("reported_incidents")
    public int reported_incidents;

    public int getReported_incidents() {
        return reported_incidents;
    }

    public void setReported_incidents(int reported_incidents) {
        this.reported_incidents = reported_incidents;
    }
}
