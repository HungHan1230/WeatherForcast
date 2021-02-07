package com.example.weatherforcast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<String[]> adapter_data;
    Context ct;

    public MyAdapter(Context context, ArrayList<String[]> data ){
        ct = context;
        adapter_data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Date_txt.setText(adapter_data.get(position)[0]);
        holder.MaxMin_Temp.setText(adapter_data.get(position)[1]);
        holder.Status_txt.setText(adapter_data.get(position)[2]);
        holder.Body_Value.setText(adapter_data.get(position)[3]);
        holder.Rain_Value.setText(adapter_data.get(position)[4]);
        holder.Humidity_Value.setText(adapter_data.get(position)[5]);
        holder.UV_Value.setText(adapter_data.get(position)[6]);
        holder.Wind_Value.setText(adapter_data.get(position)[7]);

    }

    @Override
    public int getItemCount() {
        return adapter_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Date_txt, MaxMin_Temp, Status_txt, Body_Value, Rain_Value, Humidity_Value, UV_Value, Wind_Value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Date_txt = itemView.findViewById(R.id.Date_txt);
            MaxMin_Temp = itemView.findViewById(R.id.MaxMin_Temp);
            Status_txt = itemView.findViewById(R.id.Status_txt);
            Body_Value = itemView.findViewById(R.id.Body_Value);
            Rain_Value = itemView.findViewById(R.id.Rain_Value);
            Humidity_Value = itemView.findViewById(R.id.Humidity_Value);
            UV_Value = itemView.findViewById(R.id.UV_Value);
            Wind_Value = itemView.findViewById(R.id.Wind_Value);

        }
    }
}
