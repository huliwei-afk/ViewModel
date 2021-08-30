package com.example.livedata.mediatorLiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.example.livedata.R;

public class MediatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediator);

        MutableLiveData<String> mutableLiveData1 = new MutableLiveData<>();
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>();
        MediatorLiveData liveDataMerger = new MediatorLiveData<String>();

        //合并两个数据源
        liveDataMerger.addSource(mutableLiveData1, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.d("Main", "onChanged1:" + o.toString());
            }
        });

        //合并两个数据源
        liveDataMerger.addSource(mutableLiveData2, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.d("Main", "onChanged2:" + o.toString());
            }
        });

        liveDataMerger.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.d("Main", "onChanged:" + o.toString());
            }
        });

        //这样任意一个数据源有消息变动都可以观察到
        mutableLiveData1.postValue("Android进阶之光");
    }
}