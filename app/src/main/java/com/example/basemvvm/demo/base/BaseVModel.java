package com.example.basemvvm.demo.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.lifecycle.ViewModel;

public class BaseVModel extends ViewModel {
    private HandlerThread handlerThread;
    private Handler threadHandler;

    protected Looper getThreadLooper() {
        if(handlerThread == null) {
            handlerThread = new HandlerThread("HandlerThread");
            handlerThread.start();
        }
        return handlerThread.getLooper();
    }

    protected Handler getThreadHandler() {
        if(threadHandler == null) {
            threadHandler = new Handler(getThreadLooper());
        }
        return threadHandler;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(threadHandler != null) {
            threadHandler.removeCallbacksAndMessages(null);
            threadHandler = null;
        }
        if(handlerThread != null) {
            handlerThread.quit();
            handlerThread = null;
        }
    }
}
