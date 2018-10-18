package com.example.quekai.sensordatacollector;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.PublicKey;

public class DataCollectorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView accelerometerData;
    TextView magneticFieldData;
    TextView orientationData;
    TextView gyroscopeData;
    TextView lightData;
    TextView pressureData;
    TextView temperatureData;
    TextView proximityData;
    TextView gravityData;
    TextView linearAccelerationData;
    TextView rotationVectorData;

    LinearLayout accelerometerLayout;
    LinearLayout magneticFieldLayout;
    LinearLayout orientationLayout;
    LinearLayout gyroscopeLayout;
    LinearLayout lightLayout;
    LinearLayout pressureLayout;
    LinearLayout temperatureLayout;
    LinearLayout proximityLayout;
    LinearLayout gravityLayout;
    LinearLayout linearAccelerationLayout;
    LinearLayout rotationVectorLayout;



    private SensorManager manager;




    public static StringBuffer write_buffer;


    public static boolean START = false;
    public static boolean PAUSE = false;
    public static boolean STOP = false;

    public static StringBuilder accelerometer_buffer;
    public static StringBuilder magnetic_field_buffer;
    public static StringBuilder orientation_buffer;
    public static StringBuilder gyroscope_buffer;
    public static StringBuilder light_buffer;
    public static StringBuilder pressure_buffer;
    public static StringBuilder temperature_buffer;
    public static StringBuilder proximity_buffer;
    public static StringBuilder gravity_buffer;
    public static StringBuilder linear_acceleration_buffer;
    public static StringBuilder rotation_vector_buffer;





    public static File accelerometertagetfile;
    public static File magneticfieldfile;
    public static File orientationfile;
    public static File gyroscopefile;
    public static File lightfile;
    public static File pressurefile;
    public static File temperaturefile;
    public static File proximityfile;
    public static File gravityfile;
    public static File linearaccelerationfile;
    public static File rotationvectorfile;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector);

        Button collectorSetting = findViewById(R.id.collector_setting);
        collectorSetting.setOnClickListener(this);

        accelerometerLayout=findViewById(R.id.accelerometer_layout);
        magneticFieldLayout=findViewById(R.id.magnetic_field_layout);
        orientationLayout=findViewById(R.id.orientation_layout);
        gyroscopeLayout=findViewById(R.id.gyroscope_layout);
        lightLayout=findViewById(R.id.light_layout);
        pressureLayout=findViewById(R.id.pressure_layout);
        temperatureLayout=findViewById(R.id.temperature_layout);
        proximityLayout=findViewById(R.id.proximity_layout);
        gravityLayout=findViewById(R.id.gravity_layout);
        linearAccelerationLayout=findViewById(R.id.linear_acceleration_layout);
        rotationVectorLayout=findViewById(R.id.rotation_vector_layout);


        accelerometerData = findViewById(R.id.data_accelerometer);
        magneticFieldData = findViewById(R.id.data_magnetic_field);
        orientationData = findViewById(R.id.data_orientation);
        gyroscopeData = findViewById(R.id.data_gyroscope);
        lightData = findViewById(R.id.data_light);
        pressureData = findViewById(R.id.data_pressure);
        temperatureData = findViewById(R.id.data_temperature);
        proximityData = findViewById(R.id.data_proximity);
        gravityData = findViewById(R.id.data_gravity);
        linearAccelerationData = findViewById(R.id.data_linear_acceleration);
        rotationVectorData = findViewById(R.id.data_rotation_vector);

        checkVisibility();

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        Button start = findViewById(R.id.start);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);



        accelerometer_buffer = new StringBuilder();
        magnetic_field_buffer = new StringBuilder();
        gyroscope_buffer = new StringBuilder();
        orientation_buffer = new StringBuilder();
        light_buffer = new StringBuilder();
        pressure_buffer = new StringBuilder();
        temperature_buffer = new StringBuilder();
        proximity_buffer = new StringBuilder();
        gravity_buffer = new StringBuilder();
        linear_acceleration_buffer = new StringBuilder();
        rotation_vector_buffer = new StringBuilder();


    }


    protected void onResume(){
        super.onResume();


        //加速度
        Sensor accelerometer_sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                    float[] values = event.values;
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("X方向的加速度为：").append(values[0]).append("m/s^2").append("\n");
                    buffer.append("Y方向的加速度为：").append(values[1]).append("m/s^2").append("\n");
                    buffer.append("Z方向的加速度为：").append(values[2]).append("m/s^2").append("\n");


                    accelerometer_buffer.append(buffer);


                    if(START)
                    {
                        accelerometerData.setText(buffer);


                    } else if(PAUSE){

                    }else if(STOP){

                        manager.unregisterListener(this);
                    }





            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, accelerometer_sensor, SensorManager.SENSOR_DELAY_FASTEST);



        //磁力
        Sensor magneticField_sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("X轴磁场： ").append(values[0]).append("uT").append("\n");
                buffer.append("Y轴磁场： ").append(values[1]).append("uT").append("\n");
                buffer.append("Z轴磁场： ").append(values[2]).append("uT").append("\n");


                magnetic_field_buffer.append(buffer);
                if(START)
                {
                    magneticFieldData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },magneticField_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        //方向
        Sensor orientation_sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("azimuth： ").append(values[0]).append("度").append("\n");
                buffer.append("pitch： ").append(values[1]).append("度").append("\n");
                buffer.append("roll： ").append(values[2]).append("度").append("\n");


                orientation_buffer.append(buffer);
                if(START)
                {
                    orientationData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },orientation_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor gyroscope_sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("X轴角加速度： ").append(values[0]).append("r/s").append("\n");
                buffer.append("Y轴角加速度： ").append(values[1]).append("r/s").append("\n");
                buffer.append("Z轴角加速度： ").append(values[2]).append("r/s").append("\n");




                gyroscope_buffer.append(buffer);
                if(START)
                {
                    gyroscopeData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },gyroscope_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor light_sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("亮度： ").append(values[0]).append("lux").append("\n");



                light_buffer.append(buffer);
                if(START)
                {
                    lightData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },light_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor pressure_sensor = manager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("压强： ").append(values[0]).append("hPa").append("\n");




                pressure_buffer.append(buffer);
                if(START)
                {
                    pressureData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },pressure_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor temperature_sensor = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("温度： ").append(values[0]).append("度").append("\n");



                temperature_buffer.append(buffer);
                if(START)
                {
                    temperatureData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },temperature_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor proximity_sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("距离： ").append(values[0]).append("\n");



                proximity_buffer.append(buffer);
                if(START)
                {
                    proximityData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },proximity_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor gravity_sensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("X方向重力加速度： ").append(values[0]).append("m/s^2").append("\n");
                buffer.append("Y方向重力加速度： ").append(values[1]).append("m/s^2").append("\n");
                buffer.append("Z方向重力加速度： ").append(values[2]).append("m/s^2").append("\n");




                gravity_buffer.append(buffer);
                if(START)
                {
                    gravityData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },gravity_sensor,SensorManager.SENSOR_DELAY_FASTEST);

        Sensor linear_acceleration_sensor = manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("X方向线性加速度： ").append(values[0]).append("m/s^2").append("\n");
                buffer.append("Y方向线性加速度： ").append(values[1]).append("m/s^2").append("\n");
                buffer.append("Z方向线性加速度： ").append(values[2]).append("m/s^2").append("\n");




                linear_acceleration_buffer .append(buffer);
                if(START)
                {
                    linearAccelerationData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },linear_acceleration_sensor,SensorManager.SENSOR_DELAY_FASTEST);


        Sensor rotation_vector_sensor = manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append("x*sin(theta/2)： ").append(values[0]).append("\n");
                buffer.append("y*sin(theta/2)： ").append(values[1]).append("\n");
                buffer.append("z*sin(theta/2)： ").append(values[2]).append("\n");




                rotation_vector_buffer.append(buffer);
                if(START)
                {
                    rotationVectorData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },rotation_vector_sensor,SensorManager.SENSOR_DELAY_FASTEST);







    }


    public void writeFileSdcard(File targetfile, String message) {
        try {

            RandomAccessFile raf = new RandomAccessFile(targetfile,"rw");
            raf.seek(targetfile.length());
            raf.write(message.getBytes());
            raf.close();

            //Toast.makeText(this,"存储成功",Toast.LENGTH_SHORT).show();


        } catch(Exception e) {
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(),"存储失败",Toast.LENGTH_SHORT).show();
        }
    }



    private void checkVisibility() {
        if (!CollectorSettingActivity.is_accelerometer) accelerometerLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_magnetic_field) magneticFieldLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_orientation) orientationLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_gyroscope) gyroscopeLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_light) lightLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_pressure) pressureLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_temperature) temperatureLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_proximity) proximityLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_gravity) gravityLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_linear_acceleration) linearAccelerationLayout.setVisibility(View.GONE);
        if (!CollectorSettingActivity.is_rotation_vector) rotationVectorLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collector_setting:
                Intent setting_intent = new Intent(this,CollectorSettingActivity.class);
                startActivity(setting_intent);
                break;
            case R.id.start:

                Toast.makeText(DataCollectorActivity.this,"START",Toast.LENGTH_SHORT).show();
                START = true;
                PAUSE = false;
                STOP = false;
                break;
            case R.id.pause:
                Toast.makeText(DataCollectorActivity.this,"PAUSE",Toast.LENGTH_SHORT).show();
                PAUSE = true;
                START = false;
                STOP = false;
                break;
            case R.id.stop:
                Toast.makeText(DataCollectorActivity.this,"STOP",Toast.LENGTH_SHORT).show();
                String SDCardPath = "/storage/emulated/0";

                accelerometertagetfile = new File(SDCardPath  + "/accelerometer.txt");
                magneticfieldfile = new File(SDCardPath + "/magneticfield.txt");
                orientationfile = new File(SDCardPath + "/orientation.txt");
                gyroscopefile = new File(SDCardPath + "/gyroscope.txt");
                lightfile = new File(SDCardPath + "/light.txt");
                pressurefile = new File(SDCardPath + "/pressure.txt");
                temperaturefile = new File(SDCardPath + "/temperature.txt");
                proximityfile = new File(SDCardPath + "/proximity.txt");
                gravityfile = new File(SDCardPath + "/gravity.txt");
                linearaccelerationfile = new File(SDCardPath + "/linearacceleration.txt");
                rotationvectorfile = new File(SDCardPath + "/rotationvector.txt");

                writeFileSdcard(accelerometertagetfile,accelerometer_buffer.toString());
                writeFileSdcard(magneticfieldfile,magnetic_field_buffer.toString());
                writeFileSdcard(orientationfile,orientation_buffer.toString());
                writeFileSdcard(gyroscopefile,gyroscope_buffer.toString());
                writeFileSdcard(lightfile,light_buffer.toString());
                writeFileSdcard(pressurefile,pressure_buffer.toString());
                writeFileSdcard(temperaturefile,temperature_buffer.toString());
                writeFileSdcard(proximityfile,proximity_buffer.toString());
                writeFileSdcard(gravityfile,gravity_buffer.toString());
                writeFileSdcard(linearaccelerationfile,linear_acceleration_buffer.toString());
                writeFileSdcard(rotationvectorfile,rotation_vector_buffer.toString());

                STOP = true;
                START = false;
                PAUSE = false;
                break;
            default:
                break;
        }
    }
}
