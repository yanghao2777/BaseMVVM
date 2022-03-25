package com.example.basemvvm.demo.main;


import com.example.basemvvm.demo.base.BaseVActivity;
import com.example.basemvvm.demo.databinding.ActivityMainBinding;

public class MainActivity extends BaseVActivity<MainVModel, ActivityMainBinding> {

    @Override
    protected void initView() {
        viewBinding.stateText.setText("processing");
        showProgressDialog();
        viewModel.processSomething();
    }

    @Override
    protected void dataObserver() {
        viewModel.liveInt.observe(this,it -> {
            dismissProgressDialog();
            viewBinding.stateText.setText("process finish with result :" + it);
        });
    }

    @Override
    protected void initClickListener() {

    }
}