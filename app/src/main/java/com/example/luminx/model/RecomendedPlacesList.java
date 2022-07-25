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
}