package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

public class Root5 {

    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
