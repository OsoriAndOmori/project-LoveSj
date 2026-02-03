import 'package:korean_lunar_utils/korean_lunar_utils.dart';

DateTime dateOnly(DateTime date) => DateTime(date.year, date.month, date.day);

String formatReadableDate(DateTime date) {
  return '${date.year}. ${date.month}. ${date.day}';
}

int countDdayFrom(DateTime standard, DateTime target) {
  final standardDate = dateOnly(standard);
  final targetDate = dateOnly(target);
  return standardDate.difference(targetDate).inDays;
}

int countDaysIncludeStandardFrom(DateTime start, DateTime target) {
  final startDate = dateOnly(start);
  final targetDate = dateOnly(target);
  final count = targetDate.difference(startDate).inDays + 1;
  if (count < 1) {
    throw StateError('날짜 카운팅하는 것에 0 이하는 있을 수 없다. 연애는 1일부터 셈');
  }
  return count;
}

DateTime upcomingBirthday(DateTime birth, bool isLunar) {
  final now = dateOnly(DateTime.now());
  final birthdayThisYear = DateTime(now.year, birth.month, birth.day);
  final convertedThisYear = isLunar
      ? LunarSolarConverter.convertLunarToSolar(birthdayThisYear)
      : birthdayThisYear;

  if (!convertedThisYear.isBefore(now)) {
    return convertedThisYear;
  }

  final birthdayNextYear = DateTime(now.year + 1, birth.month, birth.day);
  return isLunar
      ? LunarSolarConverter.convertLunarToSolar(birthdayNextYear)
      : birthdayNextYear;
}
