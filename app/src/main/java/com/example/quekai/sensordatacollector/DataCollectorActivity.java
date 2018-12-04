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

//    TextView accelerometerData;
//    TextView magneticFieldData;
//    TextView orientationData;
//    TextView gyroscopeData;
//    TextView lightData;
//    TextView pressureData;
//    TextView temperatureData;
//    TextView proximityData;
//    TextView gravityData;
//    TextView linearAccelerationData;
//    TextView rotationVectorData;
//
//    LinearLayout accelerometerLayout;
//    LinearLayout magneticFieldLayout;
//    LinearLayout orientationLayout;
//    LinearLayout gyroscopeLayout;
//    LinearLayout lightLayout;
//    LinearLayout pressureLayout;
//    LinearLayout temperatureLayout;
//    LinearLayout proximityLayout;
//    LinearLayout gravityLayout;
//    LinearLayout linearAccelerationLayout;
//    LinearLayout rotationVectorLayout;

    TextView guideText;






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

    public static int freq;
    
    public static int guideIndex = 0;

    String[] labelLists = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};

    public static StringBuilder accelerometer_buffer_info;
    public static StringBuilder magnetic_field_buffer_info;
    public static StringBuilder orientation_buffer_info;
    public static StringBuilder gyroscope_buffer_info;
    public static StringBuilder light_buffer_info;
    public static StringBuilder pressure_buffer_info;
    public static StringBuilder temperature_buffer_info;
    public static StringBuilder proximity_buffer_info;
    public static StringBuilder gravity_buffer_info;
    public static StringBuilder linear_acceleration_buffer_info;
    public static StringBuilder rotation_vector_buffer_info;

    public static String freq_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collector);

        Button collectorSetting = findViewById(R.id.collector_setting);
        collectorSetting.setOnClickListener(this);

//        accelerometerLayout=findViewById(R.id.accelerometer_layout);
//        magneticFieldLayout=findViewById(R.id.magnetic_field_layout);
//        orientationLayout=findViewById(R.id.orientation_layout);
//        gyroscopeLayout=findViewById(R.id.gyroscope_layout);
//        lightLayout=findViewById(R.id.light_layout);
//        pressureLayout=findViewById(R.id.pressure_layout);
//        temperatureLayout=findViewById(R.id.temperature_layout);
//        proximityLayout=findViewById(R.id.proximity_layout);
//        gravityLayout=findViewById(R.id.gravity_layout);
//        linearAccelerationLayout=findViewById(R.id.linear_acceleration_layout);
//        rotationVectorLayout=findViewById(R.id.rotation_vector_layout);
//
//
//        accelerometerData = findViewById(R.id.data_accelerometer);
//        magneticFieldData = findViewById(R.id.data_magnetic_field);
//        orientationData = findViewById(R.id.data_orientation);
//        gyroscopeData = findViewById(R.id.data_gyroscope);
//        lightData = findViewById(R.id.data_light);
//        pressureData = findViewById(R.id.data_pressure);
//        temperatureData = findViewById(R.id.data_temperature);
//        proximityData = findViewById(R.id.data_proximity);
//        gravityData = findViewById(R.id.data_gravity);
//        linearAccelerationData = findViewById(R.id.data_linear_acceleration);
//        rotationVectorData = findViewById(R.id.data_rotation_vector);
//
//        checkVisibility();

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        Button start = findViewById(R.id.start);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if(CollectorSettingActivity.is_freq_fast) freq = 0;
        if(CollectorSettingActivity.is_freq_game) freq = 1;
        if(CollectorSettingActivity.is_freq_ui) freq = 2;
        if(CollectorSettingActivity.is_freq_normal) freq = 3;


        guideText = findViewById(R.id.guide_text);



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

        accelerometer_buffer_info = new StringBuilder();
        magnetic_field_buffer_info = new StringBuilder();
        gyroscope_buffer_info = new StringBuilder();
        orientation_buffer_info = new StringBuilder();
        light_buffer_info = new StringBuilder();
        pressure_buffer_info = new StringBuilder();
        temperature_buffer_info = new StringBuilder();
        proximity_buffer_info = new StringBuilder();
        gravity_buffer_info = new StringBuilder();
        linear_acceleration_buffer_info = new StringBuilder();
        rotation_vector_buffer_info = new StringBuilder();



        if(freq == 0){
            freq_s = "SensorManager.SENSOR_DELAY_FASTEST";
        }else if (freq == 1){
            freq_s = "SensorManager.SENSOR_DELAY_GAME";

        }else if (freq == 2){
            freq_s = "SensorManager.SENSOR_DELAY_UI";

        }else if (freq == 3){
            freq_s = "SensorManager.SENSOR_DELAY_NORMAL";

        }



        //测试条件
        accelerometer_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("X方向的加速度,Y方向的加速度,Z方向的加速度")
                .append("，  单位：").append("m/s^2").append("\n");

        magnetic_field_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("X轴磁场，Y轴磁场，Z轴磁场")
                .append("，  单位：").append("uT").append("\n");

        orientation_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("azimuth,pitch,roll")
                .append("，  单位：").append("度").append("\n");

        gyroscope_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("X轴角加速度，Y轴角加速度，Z轴角加速度")
                .append("，  单位：").append("r/s").append("\n");

        light_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("亮度")
                .append("，  单位：").append("lux").append("\n");

        pressure_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("压强")
                .append("，  单位：").append("hPa").append("\n");

        temperature_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("温度")
                .append("，  单位：").append("度").append("\n");

        proximity_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("距离")
                .append("，  单位：").append(" ").append("\n");

        gravity_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("X方向重力加速度,Y方向重力加速度,Z方向重力加速度")
                .append("，  单位：").append("m/s^2").append("\n");

        linear_acceleration_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("X方向的线性加速度,Y方向的线性加速度,Z方向的线性加速度")
                .append("，  单位：").append("m/s^2").append("\n");

        rotation_vector_buffer_info.append("采样周期：").append(freq_s)
                .append("，  输出数据名称：").append("x*sin(theta/2),y*sin(theta/2),z*sin(theta/2)")
                .append("，  单位：").append(" ").append("\n");



        Button setGuide = findViewById(R.id.set_guide);
        setGuide.setOnClickListener(this);


        if(CollectorSettingActivity.is_accelerometer) guideText.setText(accelerometer_buffer_info);
        if(CollectorSettingActivity.is_magnetic_field) guideText.setText(magnetic_field_buffer_info);
        if(CollectorSettingActivity.is_orientation) guideText.setText(orientation_buffer_info);
        if(CollectorSettingActivity.is_gyroscope) guideText.setText(gyroscope_buffer_info);
        if(CollectorSettingActivity.is_light) guideText.setText(light_buffer_info);
        if(CollectorSettingActivity.is_pressure) guideText.setText(pressure_buffer_info);
        if(CollectorSettingActivity.is_temperature) guideText.setText(temperature_buffer_info);
        if(CollectorSettingActivity.is_proximity) guideText.setText(proximity_buffer_info);
        if(CollectorSettingActivity.is_gravity) guideText.setText(gravity_buffer_info);
        if(CollectorSettingActivity.is_linear_acceleration) guideText.setText(linear_acceleration_buffer_info);
        if(CollectorSettingActivity.is_rotation_vector) guideText.setText(rotation_vector_buffer_info);


    }


    protected void onResume(){
        super.onResume();



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



//    private void checkVisibility() {
//        if (!CollectorSettingActivity.is_accelerometer) accelerometerLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_magnetic_field) magneticFieldLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_orientation) orientationLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_gyroscope) gyroscopeLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_light) lightLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_pressure) pressureLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_temperature) temperatureLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_proximity) proximityLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_gravity) gravityLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_linear_acceleration) linearAccelerationLayout.setVisibility(View.GONE);
//        if (!CollectorSettingActivity.is_rotation_vector) rotationVectorLayout.setVisibility(View.GONE);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collector_setting:
                Intent setting_intent = new Intent(this,CollectorSettingActivity.class);
                startActivity(setting_intent);
                break;
            case R.id.start:

                Toast.makeText(DataCollectorActivity.this,"START",Toast.LENGTH_SHORT).show();
                accelerometer_buffer.delete(0,accelerometer_buffer.length());
                magnetic_field_buffer.delete(0,magnetic_field_buffer.length());
                orientation_buffer.delete(0,orientation_buffer.length());
                gyroscope_buffer.delete(0,gyroscope_buffer.length());
                light_buffer.delete(0,light_buffer.length());
                pressure_buffer.delete(0,pressure_buffer.length());
                temperature_buffer.delete(0,temperature_buffer.length());
                proximity_buffer.delete(0,proximity_buffer.length());
                gravity_buffer.delete(0,gravity_buffer.length());
                linear_acceleration_buffer.delete(0,linear_acceleration_buffer.length());
                rotation_vector_buffer.delete(0,rotation_vector_buffer.length());
                START = true;
                PAUSE = false;
                STOP = false;
                registersensors();
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

                new File("/storage/emulated/0/sensordata").mkdir();

                if(CollectorSettingActivity.is_accelerometer) new File("/storage/emulated/0/sensordata/accelerometer").mkdir();
                if(CollectorSettingActivity.is_magnetic_field) new File("/storage/emulated/0/sensordata/magneticfield").mkdir();
                if(CollectorSettingActivity.is_orientation) new File("/storage/emulated/0/sensordata/orientation").mkdir();
                if(CollectorSettingActivity.is_gyroscope) new File("/storage/emulated/0/sensordata/gyroscope").mkdir();
                if(CollectorSettingActivity.is_light) new File("/storage/emulated/0/sensordata/light").mkdir();
                if(CollectorSettingActivity.is_pressure) new File("/storage/emulated/0/sensordata/pressure").mkdir();
                if(CollectorSettingActivity.is_temperature) new File("/storage/emulated/0/sensordata/temperature").mkdir();
                if(CollectorSettingActivity.is_proximity) new File("/storage/emulated/0/sensordata/proximity").mkdir();
                if(CollectorSettingActivity.is_gravity) new File("/storage/emulated/0/sensordata/gravity").mkdir();
                if(CollectorSettingActivity.is_linear_acceleration) new File("/storage/emulated/0/sensordata/linearacceleration").mkdir();
                if(CollectorSettingActivity.is_rotation_vector) new File("/storage/emulated/0/sensordata/rotationvector").mkdir();

                SDCardPath = "/storage/emulated/0/sensordata";

                accelerometertagetfile = new File(SDCardPath  + "/accelerometer/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                magneticfieldfile = new File(SDCardPath + "/magneticfield/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                orientationfile = new File(SDCardPath + "/orientation/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                gyroscopefile = new File(SDCardPath + "/gyroscope/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                lightfile = new File(SDCardPath + "/light/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                pressurefile = new File(SDCardPath + "/pressure/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                temperaturefile = new File(SDCardPath + "/temperature/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                proximityfile = new File(SDCardPath + "/proximity/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                gravityfile = new File(SDCardPath + "/gravity/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                linearaccelerationfile = new File(SDCardPath + "/linearacceleration/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");
                rotationvectorfile = new File(SDCardPath + "/rotationvector/"+labelLists[guideIndex]+"-"+System.currentTimeMillis()+".txt");

                if(CollectorSettingActivity.is_accelerometer) writeFileSdcard(accelerometertagetfile,accelerometer_buffer.toString());
                if(CollectorSettingActivity.is_magnetic_field) writeFileSdcard(magneticfieldfile,magnetic_field_buffer.toString());
                if(CollectorSettingActivity.is_orientation) writeFileSdcard(orientationfile,orientation_buffer.toString());
                if(CollectorSettingActivity.is_gyroscope) writeFileSdcard(gyroscopefile,gyroscope_buffer.toString());
                if(CollectorSettingActivity.is_light) writeFileSdcard(lightfile,light_buffer.toString());
                if(CollectorSettingActivity.is_pressure) writeFileSdcard(pressurefile,pressure_buffer.toString());
                if(CollectorSettingActivity.is_temperature) writeFileSdcard(temperaturefile,temperature_buffer.toString());
                if(CollectorSettingActivity.is_proximity) writeFileSdcard(proximityfile,proximity_buffer.toString());
                if(CollectorSettingActivity.is_gravity) writeFileSdcard(gravityfile,gravity_buffer.toString());
                if(CollectorSettingActivity.is_linear_acceleration) writeFileSdcard(linearaccelerationfile,linear_acceleration_buffer.toString());
                if(CollectorSettingActivity.is_rotation_vector) writeFileSdcard(rotationvectorfile,rotation_vector_buffer.toString());

                STOP = true;
                START = false;
                PAUSE = false;
                guideIndex += 1;
                if(guideIndex == 16){
                    guiderstart();
                }
                guiderstep();

                break;
            case R.id.set_guide:
                guiderstart();
                break;
            default:
                break;
        }
    }

    private void guiderstart() {
        guideIndex = 0;

        guideText.setText(labelLists[guideIndex]);


    }

    private void guiderstep() {
        guideText.setText(labelLists[guideIndex]);


    }


    private void registersensors() {
        //加速度

        Sensor accelerometer_sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");


                accelerometer_buffer.append(buffer);


                if(START)
                {
//                    accelerometerData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }





            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, accelerometer_sensor, freq);



        //磁力
        Sensor magneticField_sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");


                magnetic_field_buffer.append(buffer);
                if(START)
                {
//                    magneticFieldData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },magneticField_sensor,freq);

        //方向
        Sensor orientation_sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");


                orientation_buffer.append(buffer);
                if(START)
                {
//                    orientationData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },orientation_sensor,freq);

        Sensor gyroscope_sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");




                gyroscope_buffer.append(buffer);
                if(START)
                {
//                    gyroscopeData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },gyroscope_sensor,freq);

        Sensor light_sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append("\n");



                light_buffer.append(buffer);
                if(START)
                {
//                    lightData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },light_sensor,freq);

        Sensor pressure_sensor = manager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append("\n");




                pressure_buffer.append(buffer);
                if(START)
                {
//                    pressureData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },pressure_sensor,freq);

        Sensor temperature_sensor = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append("\n");



                temperature_buffer.append(buffer);
                if(START)
                {
//                    temperatureData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }




            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },temperature_sensor,freq);

        Sensor proximity_sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append("\n");



                proximity_buffer.append(buffer);
                if(START)
                {
//                    proximityData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },proximity_sensor,freq);

        Sensor gravity_sensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");




                gravity_buffer.append(buffer);
                if(START)
                {
//                    gravityData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },gravity_sensor,freq);

        Sensor linear_acceleration_sensor = manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");




                linear_acceleration_buffer .append(buffer);
                if(START)
                {
//                    linearAccelerationData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },linear_acceleration_sensor,freq);


        Sensor rotation_vector_sensor = manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                StringBuffer buffer = new StringBuffer();
                buffer.append(values[0]).append(",").append(values[1]).append(",").append(values[2]).append("\n");




                rotation_vector_buffer.append(buffer);
                if(START)
                {
//                    rotationVectorData.setText(buffer);


                } else if(PAUSE){

                }else if(STOP){

                    manager.unregisterListener(this);
                }



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },rotation_vector_sensor,freq);


    }
}
