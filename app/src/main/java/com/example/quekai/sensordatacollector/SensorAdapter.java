package com.example.quekai.sensordatacollector;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by quekai on 2018/10/15.
 */

public class SensorAdapter extends ArrayAdapter {

    private int resourceId;

    public SensorAdapter(Context context, int textViewResourceId, List<MSensor> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MSensor mSensor = (MSensor) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView sensor_Type = view.findViewById(R.id.sensor_type);
        TextView sensor_Name = view.findViewById(R.id.sensor_name);
        TextView sensor_Version = view.findViewById(R.id.sensor_version);
        TextView sensor_Vendor = view.findViewById(R.id.sensor_vendor);
        TextView max_Range = view.findViewById(R.id.sensor_max_range);
        TextView min_Delay = view.findViewById(R.id.sensor_min_delay);
        TextView power = view.findViewById(R.id.sensor_power);
        TextView resolution = view.findViewById(R.id.sensor_resolution);

        sensor_Type.setText("sensor type: " + String.valueOf(mSensor.getType()));
        sensor_Name.setText("sensor name: " + String.valueOf(mSensor.getName()));
        sensor_Version.setText("sensor version: " + String.valueOf(mSensor.getVersion()));
        sensor_Vendor.setText("sensor vendor: " + String.valueOf(mSensor.getVendor()));
        max_Range.setText("sensor max range: " + String.valueOf(mSensor.getMax_range()));
        min_Delay.setText("sensor min delay: " + String.valueOf(mSensor.getMin_delay()));
        power.setText("sensor power: " + String.valueOf(mSensor.getPower()));
        resolution.setText("sensor resolution: " + String.valueOf(mSensor.getResolution()));


        return view;
    }

}
