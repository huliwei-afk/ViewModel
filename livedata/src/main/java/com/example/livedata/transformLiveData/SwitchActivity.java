package com.example.livedata.transformLiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import android.os.Bundle;
import android.util.Log;

import com.example.livedata.R;

public class SwitchActivity extends AppCompatActivity {

    private static final String TAG = "MainAc";
    MutableLiveData<String> mutableLiveData1;
    MutableLiveData<String> mutableLiveData2;
    MutableLiveData<Boolean> liveDataSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        mutableLiveData1 = new MutableLiveData<>();
        mutableLiveData2 = new MutableLiveData<>();
        liveDataSwitch = new MutableLiveData<>();

        //switchmap必定返回一个LiveData对象
        LiveData transformedLiveData = Transformations.switchMap(liveDataSwitch, new Function<Boolean, LiveData<String>>() {
            @Override
            public LiveData<String> apply(Boolean input) {
                if(input){
                    //切换监听
                    return mutableLiveData1;
                }else{
                    return mutableLiveData2;
                }
            }
        });

        transformedLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged:" + s);
            }
        });

        liveDataSwitch.postValue(false);
        mutableLiveData1.postValue("Android进阶之光");
        mutableLiveData2.postValue("Android进阶揭秘");
    }
}