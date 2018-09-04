package com.whatchasay.phonerecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;

/**
 * Created by seokil on 18. 9. 3.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    static private String TAG = "seokil.kang::PowerConnectionReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"seokil.kang");

        if (intent == null || intent.getAction() == null) return;

        String action = intent.getAction();

        if (action.equals(ACTION_POWER_CONNECTED)) {
            Toast.makeText(context,"connected",Toast.LENGTH_SHORT).show();
            //tv.setText("connected :");
        } else if (action.equals(ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context,"disconnected",Toast.LENGTH_SHORT).show();
            //tv.setText("disconnected :");
        }

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if (usbCharge) {
            //tv.append("USB \n");
        } else {
            //tv.append("AC \n");
        }
    }
}