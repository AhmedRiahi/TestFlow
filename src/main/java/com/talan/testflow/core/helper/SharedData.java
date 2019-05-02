package com.talan.testflow.core.helper;

import java.util.HashMap;

public class SharedData {

    private final static SharedData instance = new SharedData();
    private HashMap<String,Object> data = new HashMap<>();

    private SharedData(){}

    public static SharedData getInstance(){
        return SharedData.instance;
    }


    public void putData(String name,Object value){
        this.data.put(name,value);
    }

    public Object getData(String name){
        return this.data.get(name);
    }
}
