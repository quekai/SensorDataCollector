package com.example.quekai.sensordatacollector;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;


public class CollectorSettingActivity extends AppCompatActivity implements View.OnClickListener {

    public static boolean is_accelerometer = false;
    public static boolean is_magnetic_field = false;
    public static boolean is_orientation = false;
    public static boolean is_gyroscope = false;
    public static boolean is_light = false;
    public static boolean is_pressure = false;
    public static boolean is_temperature = false;
    public static boolean is_proximity = false;
    public static boolean is_gravity = false;
    public static boolean is_linear_acceleration = false;
    public static boolean is_rotation_vector = false;

    public static boolean pre_accelerometer = false;
    public static boolean pre_magnetic_field = false;
    public static boolean pre_orientation = false;
    public static boolean pre_gyroscope = false;
    public static boolean pre_light = false;
    public static boolean pre_pressure = false;
    public static boolean pre_temperature = false;
    public static boolean pre_proximity = false;
    public static boolean pre_gravity = false;
    public static boolean pre_linear_acceleration = false;
    public static boolean pre_rotation_vector = false;

    public static boolean is_freq_fast = true;
    public static boolean is_freq_game = false;
    public static boolean is_freq_ui = false;
    public static boolean is_freq_normal = false;

    public static boolean pre_freq_fast = false;
    public static boolean pre_freq_game = false;
    public static boolean pre_freq_ui = false;
    public static boolean pre_freq_normal = false;


    CheckBox accelerometer ;
    CheckBox magnetic_field ;
    CheckBox orientation ;
    CheckBox gyroscope ;
    CheckBox light ;
    CheckBox pressure;
    CheckBox temperature;
    CheckBox proximity;
    CheckBox gravity;
    CheckBox linear_acceleration;
    CheckBox rotation_vector;

    RadioButton freq_fast;
    RadioButton freq_game;
    RadioButton freq_ui;
    RadioButton freq_normal;

//    public static boolean ex_accelerometer = false;
//    public static boolean ex_magnetic_field = false;
//    public static boolean ex_orientation = false;
//    public static boolean ex_gyroscope = false;
//    public static boolean ex_light = false;
//    public static boolean ex_pressure = false;
//    public static boolean ex_temperature = false;
//    public static boolean ex_proximity = false;
//    public static boolean ex_gravity = false;
//    public static boolean ex_linear_acceleration = false;
//    public static boolean ex_rotation_vector = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector_setting);


        accelerometer = findViewById(R.id.accelerometer);
        magnetic_field = findViewById(R.id.magnetic_field);
        orientation = findViewById(R.id.orientation);
        gyroscope = findViewById(R.id.gyroscope);
        light = findViewById(R.id.light);
        pressure = findViewById(R.id.pressure);
        temperature = findViewById(R.id.temperature);
        proximity = findViewById(R.id.proximity);
        gravity = findViewById(R.id.gravity);
        linear_acceleration = findViewById(R.id.linear_acceleration);
        rotation_vector = findViewById(R.id.rotation_vector);

        freq_fast = findViewById(R.id.freq_fast);
        freq_game = findViewById(R.id.freq_game);
        freq_ui = findViewById(R.id.freq_ui);
        freq_normal = findViewById(R.id.freq_normal);


        getSupportSensors();

        accelerometer.setChecked(pre_accelerometer);
        magnetic_field.setChecked(pre_magnetic_field);
        orientation.setChecked(pre_orientation);
        gyroscope.setChecked(pre_gyroscope);
        light.setChecked(pre_light);
        pressure.setChecked(pre_pressure);
        temperature.setChecked(pre_temperature);
        proximity.setChecked(pre_proximity);
        gravity.setChecked(pre_gravity);
        linear_acceleration.setChecked(pre_linear_acceleration);
        rotation_vector.setChecked(pre_rotation_vector);


        freq_fast.setChecked(pre_freq_fast);
        freq_game.setChecked(pre_freq_game);
        freq_ui.setChecked(pre_freq_ui);
        freq_normal.setChecked(pre_freq_normal);



        Button saveSetting = findViewById(R.id.save_setting);
        saveSetting.setOnClickListener(this);


    }

    private void getSupportSensors() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor item : sensors){
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_ACCELEROMETER)){
                accelerometer.setVisibility(View.VISIBLE);
                String s = accelerometer.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                accelerometer.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_MAGNETIC_FIELD)) {
                magnetic_field.setVisibility(View.VISIBLE);
                String s = magnetic_field.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                magnetic_field.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_ORIENTATION)) {
                orientation.setVisibility(View.VISIBLE);
                String s = orientation.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                orientation.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_GYROSCOPE)) {
                gyroscope.setVisibility(View.VISIBLE);
                String s = gyroscope.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                gyroscope.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_LIGHT)) {
                light.setVisibility(View.VISIBLE);
                String s = light.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                light.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_PRESSURE)) {
                pressure.setVisibility(View.VISIBLE);
                String s = pressure.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                pressure.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_AMBIENT_TEMPERATURE)) {
                temperature.setVisibility(View.VISIBLE);
                String s = temperature.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                temperature.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_PROXIMITY)) {
                proximity.setVisibility(View.VISIBLE);
                String s = proximity.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                proximity.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_GRAVITY)) {
                gravity.setVisibility(View.VISIBLE);
                String s = gravity.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                gravity.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_LINEAR_ACCELERATION)) {
                linear_acceleration.setVisibility(View.VISIBLE);
                String s = linear_acceleration.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                linear_acceleration.setText(s);
            }
            if(Objects.equals(item.getStringType(), Sensor.STRING_TYPE_ROTATION_VECTOR)) {
                rotation_vector.setVisibility(View.VISIBLE);
                String s = rotation_vector.getText().toString();
                s = "Sensor Type: "+item.getType()+", Sensor Name: "+item.getName()+", "+s;
                rotation_vector.setText(s);
            }


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_setting:
                is_accelerometer = accelerometer.isChecked();
                is_magnetic_field = magnetic_field.isChecked();
                is_orientation = orientation.isChecked();
                is_gyroscope = gyroscope.isChecked();
                is_light = light.isChecked();
                is_pressure = pressure.isChecked();
                is_temperature = temperature.isChecked();
                is_proximity = proximity.isChecked();
                is_gravity = gravity.isChecked();
                is_linear_acceleration = linear_acceleration.isChecked();
                is_rotation_vector = rotation_vector.isChecked();
                is_freq_fast = freq_fast.isChecked();
                is_freq_game = freq_game.isChecked();
                is_freq_ui = freq_ui.isChecked();
                is_freq_normal = freq_normal.isChecked();
                pre_accelerometer = is_accelerometer;
                pre_magnetic_field = is_magnetic_field;
                pre_orientation = is_orientation;
                pre_gyroscope = is_gyroscope;
                pre_light = is_light;
                pre_pressure = is_pressure;
                pre_temperature = is_temperature;
                pre_proximity = is_proximity;
                pre_gravity = is_gravity;
                pre_linear_acceleration= is_linear_acceleration;
                pre_rotation_vector = is_rotation_vector;
                pre_freq_fast = is_freq_fast;
                pre_freq_game = is_freq_game;
                pre_freq_ui = is_freq_ui;
                pre_freq_normal = is_freq_normal;

                Toast.makeText(this,"Setting have saved.",Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this,DataCollectorActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
