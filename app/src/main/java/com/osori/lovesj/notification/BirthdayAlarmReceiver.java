package com.osori.lovesj.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BirthdayAlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		BirthdayNotifier.notifyUpcomingBirthdays(context);
	}
}
