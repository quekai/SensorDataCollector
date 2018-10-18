package com.example.quekai.sensordatacollector;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.security.PublicKey;


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






        Button saveSetting = findViewById(R.id.save_setting);
        saveSetting.setOnClickListener(this);


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

                Toast.makeText(this,"Setting have saved.",Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this,DataCollectorActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
