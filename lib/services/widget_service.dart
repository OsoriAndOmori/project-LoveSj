import 'package:home_widget/home_widget.dart';

import '../data/birthdays.dart';
import '../utils/date_helpers.dart';

class WidgetService {
  static const String appGroupId = 'group.com.osori.lovesj';
  static const String androidWidgetName = 'LoveSjWidgetProvider';
  static const String iosWidgetName = 'LoveSjWidget';

  static Future<void> updateWidget() async {
    await HomeWidget.setAppGroupId(appGroupId);

    final next = _nextBirthdayMessage();
    await HomeWidget.saveWidgetData<String>('title', '다가오는 생일');
    await HomeWidget.saveWidgetData<String>('message', next);

    await HomeWidget.updateWidget(
      name: androidWidgetName,
      iOSName: iosWidgetName,
    );
  }

  static Future<void> refreshWidget() async {
    await updateWidget();
  }

  static String _nextBirthdayMessage() {
    final now = DateTime.now();
    var nextEntry = birthdays.first;
    var minDays = 100000;

    for (final entry in birthdays) {
      final upcoming = upcomingBirthday(entry.birth, entry.isLunar);
      final days = countDdayFrom(upcoming, now);
      if (days < minDays) {
        minDays = days;
        nextEntry = entry;
      }
    }

    final upcoming = upcomingBirthday(nextEntry.birth, nextEntry.isLunar);
    final dateText = formatReadableDate(upcoming);
    final lunarText = nextEntry.isLunar
        ? ' (음 ${nextEntry.birth.month}. ${nextEntry.birth.day})'
        : '';

    return '${nextEntry.name}  $dateText$lunarText.    D - $minDays';
  }
}
