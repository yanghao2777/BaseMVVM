package com.example.basemvvm.demo.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.basemvvm.demo.R;
import com.example.basemvvm.demo.util.BaseLog;
import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseVActivity<VM extends BaseVModel, VB extends ViewBinding> extends AppCompatActivity {

    protected VM viewModel;
    protected VB viewBinding;
    protected Handler mainHandler;

    private AlertDialog baseDlg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseLog.logLifeStateA("onCreate");

        setContentView(initViewBinding());
        mainHandler = new Handler(Looper.getMainLooper());

        initViewModel();
        initStatusBar();

        initView();
        initClickListener();
        dataObserver();
    }

    protected View initViewBinding() {
        try {
            Type genericSuperclass = this.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType != null) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Class<VB> tClass = (Class<VB>) actualTypeArguments[1];
                Method inflate = tClass.getMethod("inflate", LayoutInflater.class);
                viewBinding = (VB) inflate.invoke(viewBinding, getLayoutInflater());

                Method getRoot = tClass.getMethod("getRoot");
                return (View) getRoot.invoke(viewBinding);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract void initView();

    protected abstract void dataObserver();

    protected abstract void initClickListener();

    protected void initStatusBar() {
        ImmersionBar.with(this)
                .statusBarColor("#00000000")
                .navigationBarColor("#00000000")
                .statusBarDarkFont(false)
                .navigationBarDarkIcon(false)
                .statusBarView(getStatusView())
                .init();
    }

    protected View getStatusView() {
        return null;
    }

    private void initProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.base_loading, null);

        builder.setView(view);
        baseDlg = builder.create();
        baseDlg.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        baseDlg.setCanceledOnTouchOutside(false);
        baseDlg.setCancelable(false);
        baseDlg.setOnDismissListener(d -> {

        });
    }

    protected void showProgressDialog() {
        if (baseDlg == null) {
            initProgressDialog();
        }
        baseDlg.show();
        baseDlg.setOnDismissListener(null);
        baseDlg.getWindow().setLayout(ConvertUtils.dp2px(100f),
                ConvertUtils.dp2px(200f));
    }

    protected void showProgressDialog(DialogInterface.OnDismissListener dismissListener) {
        if (baseDlg == null) {
            initProgressDialog();
        }
        baseDlg.show();
        baseDlg.setOnDismissListener(dismissListener);
        baseDlg.getWindow().setLayout(ConvertUtils.dp2px(100f),
                ConvertUtils.dp2px(200f));
    }

    protected void dismissProgressDialog() {
        if (baseDlg != null) {
            try {
                baseDlg.dismiss();
                baseDlg.setOnDismissListener(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initViewModel() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        if (parameterizedType != null) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class<VM> tClass = (Class<VM>) actualTypeArguments[0];
            viewModel = createViewModel(tClass);
        } else {
            finish();
        }
    }


    private VM createViewModel(Class<VM> cls) {
        return new ViewModelProvider(this, createVMFactory()).get(cls);
    }

    protected ViewModelProvider.Factory createVMFactory() {
        return new ViewModelProvider.NewInstanceFactory();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BaseLog.logLifeStateA("onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        BaseLog.logLifeStateA("onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BaseLog.logLifeStateA("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BaseLog.logLifeStateA("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseLog.logLifeStateA("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseLog.logLifeStateA("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        BaseLog.logLifeStateA("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseLog.logLifeStateA("onDestroy");
        dismissProgressDialog();
        mainHandler.removeCallbacksAndMessages(null);
        mainHandler = null;
    }
}
