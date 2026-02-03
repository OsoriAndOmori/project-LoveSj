import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:flutter_timezone/flutter_timezone.dart';
import 'package:timezone/data/latest.dart' as tz;
import 'package:timezone/timezone.dart' as tz;

import '../data/birthdays.dart';
import '../utils/date_helpers.dart';

class NotificationService {
  static final FlutterLocalNotificationsPlugin _plugin =
      FlutterLocalNotificationsPlugin();
  static const String _channelId = 'birthday_reminders';
  static const String _channelName = 'Birthday Reminders';
  static const String _channelDescription = 'Birthday alerts at 9 AM.';

  static Future<void> initialize() async {
    tz.initializeTimeZones();
    final timeZoneInfo = await FlutterTimezone.getLocalTimezone();
    tz.setLocalLocation(tz.getLocation(timeZoneInfo.identifier));

    const androidInit = AndroidInitializationSettings('@mipmap/ic_launcher');
    const darwinInit = DarwinInitializationSettings();
    const initSettings = InitializationSettings(
      android: androidInit,
      iOS: darwinInit,
      macOS: darwinInit,
    );

    await _plugin.initialize(settings: initSettings);

    final androidPlugin =
        _plugin.resolvePlatformSpecificImplementation<
            AndroidFlutterLocalNotificationsPlugin>();
    await androidPlugin?.requestNotificationsPermission();

    final iosPlugin = _plugin.resolvePlatformSpecificImplementation<
        IOSFlutterLocalNotificationsPlugin>();
    await iosPlugin?.requestPermissions(
      alert: true,
      badge: true,
      sound: true,
    );

    const androidChannel = AndroidNotificationChannel(
      _channelId,
      _channelName,
      description: _channelDescription,
      importance: Importance.high,
    );

    await androidPlugin?.createNotificationChannel(androidChannel);
  }

  static Future<void> scheduleBirthdayNotifications() async {
    await _plugin.cancelAll();

    const notifyOffsets = [7, 3, 1, 0];
    final now = tz.TZDateTime.now(tz.local);

    for (final entry in birthdays) {
      final upcoming = upcomingBirthday(entry.birth, entry.isLunar);
      for (final offset in notifyOffsets) {
        final notifyDate = dateOnly(upcoming).subtract(Duration(days: offset));
        final scheduleTime = tz.TZDateTime(
          tz.local,
          notifyDate.year,
          notifyDate.month,
          notifyDate.day,
          9,
        );

        if (scheduleTime.isBefore(now)) {
          continue;
        }

        final message = offset == 0
            ? '${entry.name} 생일이 오늘이에요!'
            : '${entry.name} 생일이 $offset일 남았어요.';

        await _plugin.zonedSchedule(
          id: _notificationId(entry.name, notifyDate, offset),
          title: '생일 알림',
          body: message,
          scheduledDate: scheduleTime,
          notificationDetails: const NotificationDetails(
            android: AndroidNotificationDetails(
              _channelId,
              _channelName,
              channelDescription: _channelDescription,
              importance: Importance.high,
              priority: Priority.high,
            ),
            iOS: DarwinNotificationDetails(),
          ),
          androidScheduleMode: AndroidScheduleMode.inexactAllowWhileIdle,
        );
      }
    }
  }

  static int _notificationId(String name, DateTime date, int offset) {
    final hash = name.hashCode ^ date.year ^ date.month ^ date.day ^ offset;
    return hash & 0x7fffffff;
  }
}
