package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

public class ElementValue{
    @SerializedName("value")
    public String value;
    @SerializedName("measures")
    public String measures;
}
