package com.osori.lovesj.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.osori.lovesj.R;
import com.osori.lovesj.activity.OsoriActivity;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.utils.BirthDayUtils;
import com.osori.lovesj.utils.DayCounter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BirthdayNotifier {
	private static final int NOTIFICATION_ID = 2001;
	private static final Set<Long> NOTIFY_DAYS_BEFORE = new HashSet<>();

	static {
		NOTIFY_DAYS_BEFORE.add(7L);
		NOTIFY_DAYS_BEFORE.add(3L);
		NOTIFY_DAYS_BEFORE.add(1L);
		NOTIFY_DAYS_BEFORE.add(0L);
	}

	private BirthdayNotifier() {
	}

	public static void notifyUpcomingBirthdays(Context context) {
		BirthdayNotificationChannel.ensureCreated(context);

		List<String> upcoming = buildUpcomingMessages();
		if (upcoming.isEmpty()) {
			return;
		}

		String summary = buildSummary(context, upcoming);
		String bigText = String.join("\n", upcoming);

		PendingIntent contentIntent = PendingIntent.getActivity(
				context,
				0,
				new Intent(context, OsoriActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

		Notification notification = new Notification.Builder(context, BirthdayNotificationChannel.CHANNEL_ID)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle(context.getString(R.string.notification_birthday_title))
				.setContentText(summary)
				.setStyle(new Notification.BigTextStyle().bigText(bigText))
				.setContentIntent(contentIntent)
				.setAutoCancel(true)
				.build();

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		if (notificationManager != null) {
			notificationManager.notify(NOTIFICATION_ID, notification);
		}
	}

	private static List<String> buildUpcomingMessages() {
		List<String> upcoming = new ArrayList<>();
		LocalDate today = LocalDate.now();

		for (BirthDay birthDay : BirthDay.values()) {
			LocalDate upcomingDate = BirthDayUtils.findUpComingBirthDay(birthDay.getBirth(), birthDay.isLunarBirthDay());
			long daysUntil = DayCounter.countDdayFrom(upcomingDate, today);
			if (daysUntil < 0 || !NOTIFY_DAYS_BEFORE.contains(daysUntil)) {
				continue;
			}

			if (daysUntil == 0) {
				upcoming.add(birthDay.name() + " 생일이 오늘이에요!");
			} else {
				upcoming.add(birthDay.name() + " 생일이 " + daysUntil + "일 남았어요.");
			}
		}

		return upcoming;
	}

	private static String buildSummary(Context context, List<String> upcoming) {
		if (upcoming.size() == 1) {
			return upcoming.get(0);
		}

		return context.getString(R.string.notification_birthday_summary, upcoming.size());
	}
}
