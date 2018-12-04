package com.example.quekai.sensordatacollector;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    private List<MSensor> sensorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        getSensorList();
        SensorAdapter adapter = new SensorAdapter(SensorListActivity.this,R.layout.sensor_item,sensorList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void getSensorList() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor item : sensors){
            MSensor sensor = new MSensor(item.getType(),item.getName(),item.getVersion(),item.getVendor(),
                    item.getMaximumRange(),item.getMinDelay(),item.getPower(),item.getResolution());
            sensorList.add(sensor);

        }


    }
}
