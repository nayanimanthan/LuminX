package com.example.luminx.model;

public class Root4 {
    public String brightness;
    public String email;
    public String image;
    public String light_color;
    public String light_source;
    public Location location;
    public String name;
    public String style;

    public Root4(String brightness, String email, String image, String light_color, String light_source, Location location, String name, String style) {
        this.brightness = brightness;
        this.email = email;
        this.image = image;
        this.light_color = light_color;
        this.light_source = light_source;
        this.location = location;
        this.name = name;
        this.style = style;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
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
