package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

public class RecomendedPlacesList{

    @SerializedName("category")
    public String category;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("city")
    public String city;

    @SerializedName("continent")
    public String continent;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

}