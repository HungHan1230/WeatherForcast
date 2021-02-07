package com.example.weatherforcast.repository;

import com.example.weatherforcast.service.WeatherAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    //Singleton mode
    private static WeatherRepository weatherRepository = new WeatherRepository();

    // WeatherAPIService function returns WeatherResponse data format.
    private WeatherAPIService weatherAPIService;

    private WeatherRepository(){
        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opendata.cwb.gov.tw/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPIService = retrofit.create(WeatherAPIService.class);
    }
    public static WeatherRepository getInstance(){
        return weatherRepository;
    }
    public WeatherAPIService getAPI(){
        return weatherAPIService;
    }


}
