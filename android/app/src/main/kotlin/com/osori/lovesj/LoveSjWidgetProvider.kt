package com.osori.lovesj

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetPlugin
import es.antonborri.home_widget.HomeWidgetProvider
import org.json.JSONArray
import kotlin.math.max

class LoveSjWidgetProvider : HomeWidgetProvider() {
  override fun onUpdate(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetIds: IntArray,
    widgetData: SharedPreferences,
  ) {
    appWidgetIds.forEach { widgetId ->
      renderWidget(context, appWidgetManager, widgetId, widgetData)
    }
  }

  override fun onAppWidgetOptionsChanged(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    newOptions: Bundle,
  ) {
    super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    renderWidget(context, appWidgetManager, appWidgetId, HomeWidgetPlugin.getData(context))
  }

  override fun onReceive(context: Context, intent: Intent) {
    super.onReceive(context, intent)

    val widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1)
    if (widgetId == -1) {
      return
    }

    val appWidgetManager = AppWidgetManager.getInstance(context)
    val widgetData = HomeWidgetPlugin.getData(context)

    when (intent.action) {
      ACTION_NEXT_PAGE -> {
        shiftPage(context, appWidgetManager, widgetId, widgetData, +1)
      }
      ACTION_PREV_PAGE -> {
        shiftPage(context, appWidgetManager, widgetId, widgetData, -1)
      }
    }
  }

  override fun onDeleted(context: Context, appWidgetIds: IntArray) {
    super.onDeleted(context, appWidgetIds)
    val prefs = HomeWidgetPlugin.getData(context)
    prefs.edit().apply {
      appWidgetIds.forEach { widgetId ->
        remove(pageKey(widgetId))
      }
    }.apply()
  }

  private fun shiftPage(
    context: Context,
    appWidgetManager: AppWidgetManager,
    widgetId: Int,
    widgetData: SharedPreferences,
    direction: Int,
  ) {
    val items = readItems(widgetData)
    if (items.isEmpty()) {
      renderWidget(context, appWidgetManager, widgetId, widgetData)
      return
    }

    val pageSize = visibleRowCount(appWidgetManager.getAppWidgetOptions(widgetId))
    val pageCount = max(1, (items.size + pageSize - 1) / pageSize)
    val currentPage = currentPage(widgetData, widgetId, pageCount)
    val nextPage = (currentPage + direction).mod(pageCount)

    widgetData.edit().putInt(pageKey(widgetId), nextPage).apply()
    renderWidget(context, appWidgetManager, widgetId, widgetData)
  }

  private fun renderWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    widgetId: Int,
    widgetData: SharedPreferences,
  ) {
    val items = readItems(widgetData)
    val rows = visibleRowCount(appWidgetManager.getAppWidgetOptions(widgetId))
    val pageCount = max(1, (max(items.size, 1) + rows - 1) / rows)
    val currentPage = currentPage(widgetData, widgetId, pageCount)
    val startIndex = currentPage * rows
    val visibleItems = if (items.isEmpty()) {
      listOf("일정을 불러오는 중이에요.")
    } else {
      items.drop(startIndex).take(rows)
    }

    val views = RemoteViews(context.packageName, R.layout.love_sj_widget).apply {
      setTextViewText(
        R.id.widget_title,
        widgetData.getString("widget_title", "우리 일정") ?: "우리 일정",
      )
      setTextViewText(R.id.widget_page_indicator, "${currentPage + 1} / $pageCount")

      bindRows(this, visibleItems)

      setOnClickPendingIntent(
        R.id.widget_header,
        HomeWidgetLaunchIntent.getActivity(context, MainActivity::class.java),
      )
      setOnClickPendingIntent(
        R.id.widget_prev_button,
        navigationIntent(context, widgetId, ACTION_PREV_PAGE),
      )
      setOnClickPendingIntent(
        R.id.widget_next_button,
        navigationIntent(context, widgetId, ACTION_NEXT_PAGE),
      )
    }

    appWidgetManager.updateAppWidget(widgetId, views)
  }

  private fun bindRows(views: RemoteViews, visibleItems: List<String>) {
    ROW_VIEW_IDS.forEachIndexed { index, viewId ->
      if (index < visibleItems.size) {
        views.setViewVisibility(viewId, View.VISIBLE)
        views.setTextViewText(viewId, visibleItems[index])
      } else {
        views.setViewVisibility(viewId, View.GONE)
      }
    }
  }

  private fun navigationIntent(
    context: Context,
    widgetId: Int,
    action: String,
  ): PendingIntent {
    val intent = Intent(context, LoveSjWidgetProvider::class.java).apply {
      this.action = action
      putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
      data = Uri.parse("loveSjWidget://$action/$widgetId")
    }

    return PendingIntent.getBroadcast(
      context,
      widgetId + action.hashCode(),
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
  }

  private fun visibleRowCount(options: Bundle): Int {
    val maxHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 140)
    val availableHeight = max(72, maxHeight - 84)
    return (availableHeight / 34).coerceIn(1, MAX_ROWS)
  }

  private fun readItems(widgetData: SharedPreferences): List<String> {
    val rawJson = widgetData.getString("widget_items_json", null) ?: return emptyList()
    return try {
      val jsonArray = JSONArray(rawJson)
      List(jsonArray.length()) { index -> jsonArray.optString(index) }
        .filter { it.isNotBlank() }
    } catch (_: Exception) {
      emptyList()
    }
  }

  private fun currentPage(
    widgetData: SharedPreferences,
    widgetId: Int,
    pageCount: Int,
  ): Int {
    return widgetData.getInt(pageKey(widgetId), 0).coerceIn(0, pageCount - 1)
  }

  private fun pageKey(widgetId: Int): String = "widget_page_$widgetId"

  companion object {
    private const val ACTION_NEXT_PAGE = "com.osori.lovesj.widget.NEXT_PAGE"
    private const val ACTION_PREV_PAGE = "com.osori.lovesj.widget.PREV_PAGE"
    private const val MAX_ROWS = 6
    private val ROW_VIEW_IDS = listOf(
      R.id.widget_row_1,
      R.id.widget_row_2,
      R.id.widget_row_3,
      R.id.widget_row_4,
      R.id.widget_row_5,
      R.id.widget_row_6,
    )
  }
}
