package com.example.luminx.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root{
    @SerializedName("pollutionReports")
    public ArrayList<PollutionReport> pollutionReports;

    public ArrayList<PollutionReport> getPollutionReports() {
        return pollutionReports;
    }

    public void setPollutionReports(ArrayList<PollutionReport> pollutionReports) {
        this.pollutionReports = pollutionReports;
    }
}