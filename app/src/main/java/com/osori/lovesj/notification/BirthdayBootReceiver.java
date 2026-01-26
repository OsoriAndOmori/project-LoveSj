package com.osori.lovesj.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.osori.lovesj.widget.BirthdayWidgetScheduler;

public class BirthdayBootReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			BirthdayNotificationScheduler.scheduleDaily(context);
			BirthdayWidgetScheduler.scheduleDaily(context);
		}
	}
}
