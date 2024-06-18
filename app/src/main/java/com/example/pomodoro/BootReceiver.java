package com.example.pomodoro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Handle the boot completed event here
            NotificationHelper.createNotificationChannel(context);
            NotificationHelper.sendNotification(context, "Device rebooted. Pomodoro Timer is ready.");
        }
    }
}
