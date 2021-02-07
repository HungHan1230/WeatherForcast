package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Location_element{
    //    "locationName": "北投區",
//            "geocode": "6301200",
//            "lat": "25.134133",
//            "lon": "121.494596",
    @SerializedName("locationName")
    public String locationName;
    @SerializedName("geocode")
    public int geocode;
    @SerializedName("lat")
    public double lat;
    @SerializedName("lon")
    public double lon;
    @SerializedName("weatherElement")
    public ArrayList<WeatherElement> weatherElement;
}
