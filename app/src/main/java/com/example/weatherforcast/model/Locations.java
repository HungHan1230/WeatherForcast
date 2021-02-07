package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Locations{
    @SerializedName("locationsName")
    public String locationsName;
    @SerializedName("datasetDescription")
    public String datasetDescription;
    @SerializedName("dataid")
    public String dataid;
    @SerializedName("location")
    public ArrayList<Location_element> location = new ArrayList();
}
