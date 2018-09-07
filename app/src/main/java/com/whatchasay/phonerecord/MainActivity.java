package com.whatchasay.phonerecord;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    private int mJobId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

//        PowerConnectionReceiver mPowerConncetionRecevier = new PowerConnectionReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ACTION_POWER_CONNECTED);
//        intentFilter.addAction(ACTION_POWER_DISCONNECTED);
//        getApplication().registerReceiver(mPowerConncetionRecevier,intentFilter);
    }

    public void temp(View v) {

        JobInfo.Builder builder = new JobInfo.Builder(mJobId++, new ComponentName(this, MyJobService.class));
        builder.setRequiresCharging(true);
        builder.setPersisted(true);
        builder.setOverrideDeadline(3000);
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(builder.build());

        return;
    }

}
