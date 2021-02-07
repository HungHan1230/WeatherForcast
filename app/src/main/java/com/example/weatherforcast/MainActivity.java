package com.example.weatherforcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;


import com.example.weatherforcast.model.Location_element;
import com.example.weatherforcast.model.Time_Element;
import com.example.weatherforcast.model.WeatherData;
import com.example.weatherforcast.model.WeatherElement;
import com.example.weatherforcast.repository.WeatherRepository;
import com.example.weatherforcast.service.WeatherAPIService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    WeatherAPIService weatherAPIService;
    RecyclerView weatherRecycler;
    String[] test = {"01/08","6/7","陰天","3/5","60","78","0","N 2"};
    String[] test2 = {"01/09","6/6","陰天","3/4","60","78","0","N 2"};
    ArrayList<String[]> list = new ArrayList<>();
    Map<String, LinkedHashMap<String,String>> map;
    Location_element taipei;
    Context ct = this;
    ArrayList<LinkedHashMap<String, LinkedHashMap<String,String>>> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        list.add(test);
//        list.add(test2);
//
//        MyAdapter myAdapter = new MyAdapter(this,list);
//        weatherRecycler = findViewById(R.id.weatherRecycler);
//        weatherRecycler.setAdapter(myAdapter);
//        weatherRecycler.setLayoutManager(new LinearLayoutManager(this));

        // 透過WeatherRepository取得連線基底
        weatherAPIService = WeatherRepository.getInstance().getAPI();

        // 建立連線的Call，此處設置call為weatherAPIService中的getTaipeiWeatherData連線
        Call<WeatherData> call = weatherAPIService.getTaipeiWeatherData("CWB-47DE5B75-DD9B-48A3-BD74-3B0A7BC6335F");

        // 執行call
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                // 連線成功
                // 回傳的資料已轉成WeatherData物件，
//                Log.d("elementName:",response.body().records.locations.get(0).location.get(0).weatherElement.get(0).elementName); // PoP12h
                ArrayList<Location_element> elements = response.body().records.locations.get(0).location;
                for(Location_element entry: elements){
//                    Log.d("locationName",entry.locationName);
                    if(entry.locationName.equals("臺北市")){
                        taipei = entry;
                        break;
                    }
                }

                String[] target = {"PoP12h","RH","WS","MaxAT","Wx","MinT","UVI","MinAT","MaxT","WD"};
                Set<String> target_set = new HashSet<>(Arrays.asList(target));

                map = new LinkedHashMap<>();
                // process weather data of Taipei
                for(WeatherElement weatherElement : taipei.weatherElement){
                    LinkedHashMap<String,String> innerMap = new LinkedHashMap();
                    if(target_set.contains(weatherElement.elementName)){
                        for (Time_Element time_element : weatherElement.time){
                            innerMap.put(time_element.startTime,time_element.elementValue.get(0).value);
                            map.put(weatherElement.elementName, innerMap);
                        }

                    }
                }
//                Log.d("set:",map.get("RH").keySet().toString());
                //[2021-02-08 00:00:00, 2021-02-08 06:00:00, 2021-02-08 18:00:00, 2021-02-09 06:00:00, 2021-02-09 18:00:00.....
                String[] time_set = map.get("RH").keySet().toArray(new String[map.get("RH").keySet().size()]);
                for(int i = 0; i < time_set.length; i++){
                    // Date(innerMap.startTime), MinT/MaxT, Wx, MinAT/MaxAT, PoP12h, RH, UVI, WD/WS
                    String[] entry = new String[8];

                    // Date(innerMap.startTime)
                    entry[0] = time_set[i];

                    // MinT/MaxT
                    entry[1] = new StringBuilder()
                            .append(map.get("MinT").get(entry[0]))
                            .append("/")
                            .append(map.get("MaxT").get(entry[0]))
                            .toString();

                    // Wx
                    entry[2] = map.get("Wx").get(entry[0]);

                    // MinAT/MaxAT
                    entry[3] = new StringBuilder()
                            .append(map.get("MinAT").get(entry[0]))
                            .append("/")
                            .append(map.get("MaxAT").get(entry[0]))
                            .toString();

                    // PoP12h
                    entry[4] = map.get("PoP12h").get(entry[0]);

                    // RH
                    entry[5] = map.get("RH").get(entry[0]);

                    // UVI
                    entry[6] = map.get("UVI").get(entry[0]);

                    // WD/WS
                    entry[7] = new StringBuilder()
                            .append(map.get("WD").get(entry[0]))
                            .append("/")
                            .append(map.get("WS").get(entry[0]))
                            .toString();

                    list.add(entry);

                }

                MyAdapter myAdapter = new MyAdapter(ct,list);
                weatherRecycler = findViewById(R.id.weatherRecycler);
                weatherRecycler.setAdapter(myAdapter);
                weatherRecycler.setLayoutManager(new LinearLayoutManager(ct));



                //String[] test = {"01/08","6/7","陰天","3/5","60","78","0","N 2"};


//                Log.d("Map:", map.toString());




//                ArrayList<Time_Element> weatherElements = response.body().records.locations.get(0).location.get(0).weatherElement.get(0).time;
//                for(Time_Element entry: weatherElements){
//                    Log.d("StartTime:",entry.startTime);
//                    Log.d("EndTime:",entry.endTime);
////                    SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//
//                }

//                Testing();



            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }


        });



    }
//    public void Testing(){
//        //Get Current Time
//        Calendar mCal = Calendar.getInstance();
//        String dateformat = "yyyy-MM-dd hh:mm:ss";
//        SimpleDateFormat df = new SimpleDateFormat(dateformat);
//        String today = df.format(mCal.getTime());
//        Log.d("Current Time","today is " + today);
//
//        // string to calendar
//        String str="2010-5-27";
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        Log.d("compare",calendar.compareTo(mCal)+""); // -1, calender < mCal
//
//        // mm/dd (day) 白天 & mm/dd (day) 晚上
//    }
}