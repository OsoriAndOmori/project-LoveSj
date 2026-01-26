package com.osori.lovesj.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.osori.lovesj.R;

public final class BirthdayNotificationChannel {
	public static final String CHANNEL_ID = "birthday_reminders";

	private BirthdayNotificationChannel() {
	}

	public static void ensureCreated(Context context) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
			return;
		}

		NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
		if (notificationManager == null || notificationManager.getNotificationChannel(CHANNEL_ID) != null) {
			return;
		}

		NotificationChannel channel = new NotificationChannel(
				CHANNEL_ID,
				context.getString(R.string.notification_channel_name),
				NotificationManager.IMPORTANCE_DEFAULT);
		channel.setDescription(context.getString(R.string.notification_channel_description));
		notificationManager.createNotificationChannel(channel);
	}
}
