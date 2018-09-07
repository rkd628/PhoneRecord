package com.whatchasay.phonerecord;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * Created by seokil on 18. 9. 8.
 */

public class MyJobService extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {

        Toast.makeText(getApplicationContext(),"charging..",Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
