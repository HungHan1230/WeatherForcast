package com.example.weatherforcast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Time_Element{
//    "time": [
//    {
//        "startTime": "2021-02-06 06:00:00",
//            "endTime": "2021-02-06 18:00:00",
//            "elementValue": [
//        {
//            "value": "6",
//                "measures": "紫外線指數"
//        },
//        {
//            "value": "高量級",
//                "measures": "曝曬級數"
//        }
//                                        ]
//    },

    @SerializedName("startTime")
    public String startTime;
    @SerializedName("endTime")
    public String endTime;
    @SerializedName("elementValue")
    public ArrayList<ElementValue> elementValue = new ArrayList();
}
