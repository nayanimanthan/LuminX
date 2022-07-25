package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root3 {

    @SerializedName("message")
    public String message;

    @SerializedName("recomendedPlacesList")
    public ArrayList<RecomendedPlacesList> recomendedPlacesList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<RecomendedPlacesList> getRecomendedPlacesList() {
        return recomendedPlacesList;
    }

    public void setRecomendedPlacesList(ArrayList<RecomendedPlacesList> recomendedPlacesList) {
        this.recomendedPlacesList = recomendedPlacesList;
    }
}
