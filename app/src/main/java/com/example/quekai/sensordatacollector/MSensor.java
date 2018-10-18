package com.example.quekai.sensordatacollector;

/**
 * Created by quekai on 2018/10/15.
 */

public class MSensor {
    private int type;
    private String name;
    private int version;
    private String vendor;
    private float max_range;
    private int min_delay;
    private float power;
    private float resolution;

    public MSensor(int type,String name,int version,String vendor,
    float max_range,int min_delay, float power, float resolution){
        this.type = type;
        this.name = name;
        this.version = version;
        this.vendor = vendor;
        this.max_range = max_range;
        this.min_delay = min_delay;
        this.power = power;
        this.resolution = resolution;
    }

    public int getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public int getVersion(){
        return version;
    }

    public String getVendor(){
        return vendor;
    }

    public float getMax_range(){
        return max_range;
    }

    public int getMin_delay(){
        return min_delay;
    }

    public float getPower(){
        return power;
    }

    public float getResolution(){
        return resolution;
    }



}
