import 'dart:convert';

import 'package:home_widget/home_widget.dart';

import '../data/anniversaries.dart';
import '../data/birthdays.dart';
import '../utils/date_helpers.dart';

class WidgetService {
  static const String appGroupId = 'group.com.osori.lovesj';
  static const String androidWidgetName = 'LoveSjWidgetProvider';
  static const String iosWidgetName = 'LoveSjWidget';
  static const String widgetTitleKey = 'widget_title';
  static const String widgetItemsKey = 'widget_items_json';

  static Future<void> updateWidget() async {
    await HomeWidget.setAppGroupId(appGroupId);

    await HomeWidget.saveWidgetData<String>(widgetTitleKey, '우리 일정');
    await HomeWidget.saveWidgetData<String>(
      widgetItemsKey,
      jsonEncode(_buildWidgetItems()),
    );

    await HomeWidget.updateWidget(
      name: androidWidgetName,
      iOSName: iosWidgetName,
    );
  }

  static Future<void> refreshWidget() async {
    await updateWidget();
  }

  static List<String> _buildWidgetItems() {
    final now = DateTime.now();
    final items = <String>[
      _buildAnniversaryMessage(loveAnniversary, now),
      _buildAnniversaryMessage(weddingAnniversary, now),
    ];

    final birthdayItems = birthdays.map((entry) {
      final upcoming = upcomingBirthday(entry.birth, entry.isLunar);
      final days = countDdayFrom(upcoming, now);
      final dateText = formatReadableDate(upcoming);
      final lunarText = entry.isLunar
          ? ' (음 ${entry.birth.month}. ${entry.birth.day})'
          : '';
      return _WidgetEvent(
        sortDate: upcoming,
        message: '${entry.name}  $dateText$lunarText  D-$days',
      );
    }).toList()
      ..sort((a, b) => a.sortDate.compareTo(b.sortDate));

    items.addAll(birthdayItems.map((entry) => entry.message));
    return items;
  }

  static String _buildAnniversaryMessage(Anniversary anniversary, DateTime now) {
    final days = countDaysIncludeStandardFrom(anniversary.date, now);
    return '${anniversary.label}  ${formatReadableDate(anniversary.date)}  D+$days';
  }
}

class _WidgetEvent {
  final DateTime sortDate;
  final String message;

  const _WidgetEvent({
    required this.sortDate,
    required this.message,
  });
}
