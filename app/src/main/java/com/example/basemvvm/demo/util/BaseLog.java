package com.example.basemvvm.demo.util;

import android.util.Log;

public class BaseLog {
    public static void logLifeStateA(String str) {
        Log.d("###","#Activity life :" + str);
    }

    public static void logD(String str) {
        Log.d("###",str);
    }

    public static void logLifeStateF(String str) {
        Log.d("###","#Fragment life :" + str);
    }
}
