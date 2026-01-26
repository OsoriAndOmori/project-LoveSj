package com.osori.lovesj.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.widget.RemoteViews;

import com.osori.lovesj.R;
import com.osori.lovesj.anniversary.BirthDay;
import com.osori.lovesj.utils.BirthDayUtils;

import java.util.ArrayList;
import java.util.List;

public class BirthdayWidgetProvider extends AppWidgetProvider {
	private static final String ACTION_NEXT = "com.osori.lovesj.widget.ACTION_NEXT";
	private static final String ACTION_PREVIOUS = "com.osori.lovesj.widget.ACTION_PREVIOUS";
	private static final String ACTION_DAILY_UPDATE = "com.osori.lovesj.widget.ACTION_DAILY_UPDATE";

	private static final String EXTRA_INDEX = "com.osori.lovesj.widget.EXTRA_INDEX";

	public static Intent buildUpdateIntent(Context context) {
		Intent intent = new Intent(context, BirthdayWidgetProvider.class);
		intent.setAction(ACTION_DAILY_UPDATE);
		return intent;
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			updateWidget(context, appWidgetManager, appWidgetId, 0);
		}
		BirthdayWidgetScheduler.scheduleDaily(context);
	}

	@Override
	public void onEnabled(Context context) {
		BirthdayWidgetScheduler.scheduleDaily(context);
	}

	@Override
	public void onDisabled(Context context) {
		BirthdayWidgetScheduler.cancelDaily(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		String action = intent.getAction();
		if (action == null) {
			return;
		}

		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName componentName = new ComponentName(context, BirthdayWidgetProvider.class);
		int[] widgetIds = manager.getAppWidgetIds(componentName);

		switch (action) {
			case ACTION_NEXT:
			case ACTION_PREVIOUS:
				int currentIndex = intent.getIntExtra(EXTRA_INDEX, 0);
				for (int widgetId : widgetIds) {
					updateWidget(context, manager, widgetId, currentIndex);
				}
				break;
			case ACTION_DAILY_UPDATE:
				for (int widgetId : widgetIds) {
					updateWidget(context, manager, widgetId, 0);
				}
				break;
			default:
				break;
		}
	}

	private void updateWidget(Context context, AppWidgetManager manager, int widgetId, int index) {
		List<String> birthDayTexts = buildBirthDayTexts();
		int currentIndex = normalizeIndex(index, birthDayTexts.size());

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_birthday);
		views.setTextViewText(R.id.widget_birthday_text_primary, getTextOrEmpty(birthDayTexts, currentIndex));
		views.setTextViewText(R.id.widget_birthday_text_secondary,
				getTextOrEmpty(birthDayTexts, currentIndex + 1));

		int textSize = context.getResources().getDimensionPixelSize(R.dimen.widget_text_size);
		views.setTextViewTextSize(R.id.widget_birthday_text_primary, TypedValue.COMPLEX_UNIT_PX, textSize);
		views.setTextViewTextSize(R.id.widget_birthday_text_secondary, TypedValue.COMPLEX_UNIT_PX, textSize);

		views.setOnClickPendingIntent(
				R.id.widget_button_prev,
				buildActionIntent(context, widgetId, ACTION_PREVIOUS, currentIndex - 1));
		views.setOnClickPendingIntent(
				R.id.widget_button_next,
				buildActionIntent(context, widgetId, ACTION_NEXT, currentIndex + 1));

		manager.updateAppWidget(widgetId, views);
	}

	private static PendingIntent buildActionIntent(Context context, int widgetId, String action, int index) {
		Intent intent = new Intent(context, BirthdayWidgetProvider.class);
		intent.setAction(action);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		intent.putExtra(EXTRA_INDEX, index);
		return PendingIntent.getBroadcast(
				context,
				widgetId + action.hashCode(),
				intent,
				PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
	}

	private static int normalizeIndex(int index, int size) {
		if (size <= 0) {
			return 0;
		}
		int normalized = index % size;
		return normalized < 0 ? normalized + size : normalized;
	}

	private static String getTextOrEmpty(List<String> texts, int index) {
		if (texts.isEmpty()) {
			return "";
		}
		return texts.get(normalizeIndex(index, texts.size()));
	}

	private static List<String> buildBirthDayTexts() {
		List<String> result = new ArrayList<>();
		for (BirthDay birthDay : BirthDay.values()) {
			result.add(BirthDayUtils.buildBirthDayText(birthDay));
		}
		return result;
	}
}
