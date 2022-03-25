package com.example.basemvvm.demo.base;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.basemvvm.demo.main.MainActivity;
import com.example.basemvvm.demo.databinding.ActivityStartBinding;

public class StartActivity extends BaseVActivity<BaseVModel, ActivityStartBinding> {

    private final MutableLiveData<Boolean> startHome = new MutableLiveData<>();

    @Override
    protected void initView() {
        mainHandler.postDelayed(() -> {
            startHome.setValue(true);
        },1200);
    }

    @Override
    protected void dataObserver() {
        startHome.observe(this,it -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void initClickListener() {

    }
}