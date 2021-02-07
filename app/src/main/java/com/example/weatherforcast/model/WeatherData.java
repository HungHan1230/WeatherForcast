package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

// https://codebeautify.org/json-to-java-converter
public class WeatherData {
    @SerializedName("success")
    public String success;
    @SerializedName("result")
    public Result result;
    @SerializedName("records")
    public Records records;
}


