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

public class TransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("Main", "onChanged1:" + s);
            }
        });

        LiveData transformedLiveData = Transformations.map(mutableLiveData, new Function<String, Object>() {

            @Override
            public Object apply(String input) {
                return input + "+Android进阶揭秘";
            }
        });

        transformedLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.d("Main", "onChanged2:" + o.toString());
            }
        });

        mutableLiveData.postValue("Android进阶之光");
    }
}