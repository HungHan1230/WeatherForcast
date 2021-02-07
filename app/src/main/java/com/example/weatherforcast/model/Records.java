package com.example.weatherforcast.model;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Records {
    @SerializedName("locations")
    public ArrayList<Locations> locations = new ArrayList();
}








