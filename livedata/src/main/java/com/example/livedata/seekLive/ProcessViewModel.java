package com.example.livedata.seekLive;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProcessViewModel extends ViewModel {
    private MutableLiveData<Integer> progress;

    public MutableLiveData<Integer> getCurrentProgress(){
        if(progress == null){
            progress = new MutableLiveData<>();
            progress.setValue(0);
        }
        return progress;
    }
}