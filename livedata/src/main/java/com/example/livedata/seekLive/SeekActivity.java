package com.example.livedata.seekLive;

import android.os.Bundle;

import com.example.livedata.MyViewModel;
import com.example.livedata.R;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.SeekBar;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SeekActivity extends AppCompatActivity {

    private ProcessViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);

        TextView textView = findViewById(R.id.seekText);
        SeekBar seekBar = findViewById(R.id.seekBar);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ProcessViewModel.class);
        textView.setText("跳过片头 0s");

        viewModel.getCurrentProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText("跳过片头 " + viewModel.getCurrentProgress().getValue() + "s");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.getCurrentProgress().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}