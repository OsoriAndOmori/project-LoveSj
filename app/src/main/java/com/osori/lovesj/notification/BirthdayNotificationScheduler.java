package com.osori.lovesj.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public final class BirthdayNotificationScheduler {
	private static final int REQUEST_CODE = 1001;

	private BirthdayNotificationScheduler() {
	}

	public static void scheduleDaily(Context context) {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (alarmManager == null) {
			return;
		}

		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				context,
				REQUEST_CODE,
				new Intent(context, BirthdayAlarmReceiver.class),
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		alarmManager.setInexactRepeating(
				AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(),
				AlarmManager.INTERVAL_DAY,
				pendingIntent);
	}
}
