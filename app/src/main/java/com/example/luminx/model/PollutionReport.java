package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PollutionReport{
    @SerializedName("brightness")
    public String brightness;
    @SerializedName("date")
    public Date date;
    @SerializedName("email")
    public String email;
    @SerializedName("image")
    public String image;
    @SerializedName("light_color")
    public String light_color;
    @SerializedName("light_source")
    public String light_source;
    @SerializedName("location")
    public Location location;
    @SerializedName("name")
    public String name;
    @SerializedName("style")
    public String style;

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLight_color() {
        return light_color;
    }

    public void setLight_color(String light_color) {
        this.light_color = light_color;
    }

    public String getLight_source() {
        return light_source;
    }

    public void setLight_source(String light_source) {
        this.light_source = light_source;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}