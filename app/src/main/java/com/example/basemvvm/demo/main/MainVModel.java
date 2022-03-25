package com.example.basemvvm.demo.main;

import androidx.lifecycle.MutableLiveData;

import com.example.basemvvm.demo.base.BaseVModel;
import com.example.basemvvm.demo.util.BaseLog;

public class MainVModel extends BaseVModel {

    public MutableLiveData<Integer> liveInt = new MutableLiveData<>();

    public void processSomething() {
        getThreadHandler().postDelayed(() -> {
            BaseLog.logD("this is running in child thread");

            int x = 0;
            for(int i = 0;i < 10000;i++) {
                x++;
            }
            liveInt.postValue(x);
        },3000);
    }
}
