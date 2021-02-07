package com.example.weatherforcast.service;

import com.example.weatherforcast.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIService {
    @GET("v1/rest/datastore/F-D0047-091")
    Call<WeatherData> getTaipeiWeatherData(@Query("Authorization") String Authorization);

}
