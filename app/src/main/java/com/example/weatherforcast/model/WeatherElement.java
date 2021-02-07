package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherElement{
    //    "elementName": "PoP12h",
//            "description": "12小時降雨機率",
//            "time": [
    @SerializedName("elementName")
    public String elementName;
    @SerializedName("description")
    public String description;
    @SerializedName("time")
    public ArrayList<Time_Element> time = new ArrayList();

}
