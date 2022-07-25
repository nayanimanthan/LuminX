package com.example.luminx.apimanager;

import com.example.luminx.model.Root;
import com.example.luminx.model.Root2;
import com.example.luminx.model.Root3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://192.168.0.217:9095/";

    @GET("lightPollution/report")
    Call<Root> getLightPollutionReport();

    @GET("lightPollution/reported_incidents/location/")
    Call<Root2> getReportedIncidentscount(@Query("location") String location);

    @GET("lightPollution/recommend_place/province/province")
    Call<Root3> getRecommendationsbasedonprovince(@Query("province") String province);

    @GET("lightPollution/recommend_place/city/")
    Call<Root3> getRecommendationsbasedoncity(@Query("city") String city);
}