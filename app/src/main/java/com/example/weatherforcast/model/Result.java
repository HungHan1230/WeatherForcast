package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("resource_id")
    public String resource_id;
    @SerializedName("fields")
    public ArrayList fields = new ArrayList();
}
