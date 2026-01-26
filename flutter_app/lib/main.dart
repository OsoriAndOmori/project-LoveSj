import 'package:flutter/material.dart';
import 'package:korean_lunar_calendar/korean_lunar_calendar.dart';

void main() {
  runApp(const LoveSjApp());
}

class LoveSjApp extends StatelessWidget {
  const LoveSjApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Love SJ',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFFE91E63)),
        useMaterial3: true,
      ),
      home: const LoveHomePage(),
    );
  }
}

class LoveHomePage extends StatelessWidget {
  const LoveHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final now = DateTime.now();
    return Scaffold(
      appBar: AppBar(
        title: const Text('수진 · 기훈 D-day'),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          SectionCard(
            title: '기념일',
            children: anniversaries
                .map(
                  (anniversary) => ListTile(
                    title: Text(anniversary.name),
                    subtitle: Text(
                      '${formatDate(anniversary.date)} · D + ${countDaysIncludeStart(anniversary.date, now)}',
                    ),
                  ),
                )
                .toList(),
          ),
          const SizedBox(height: 16),
          SectionCard(
            title: '생일',
            children: birthdays
                .map(
                  (birthday) => ListTile(
                    title: Text(birthday.name),
                    subtitle: Text(buildBirthdayText(birthday, now)),
                  ),
                )
                .toList(),
          ),
        ],
      ),
    );
  }
}

class SectionCard extends StatelessWidget {
  const SectionCard({
    super.key,
    required this.title,
    required this.children,
  });

  final String title;
  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 0,
      color: Theme.of(context).colorScheme.surfaceVariant,
      child: Padding(
        padding: const EdgeInsets.all(12),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: Theme.of(context).textTheme.titleMedium,
            ),
            const Divider(),
            ...children,
          ],
        ),
      ),
    );
  }
}

class Anniversary {
  const Anniversary({
    required this.name,
    required this.date,
  });

  final String name;
  final DateTime date;
}

class Birthday {
  const Birthday({
    required this.name,
    required this.birthDate,
    required this.isLunar,
  });

  final String name;
  final DateTime birthDate;
  final bool isLunar;
}

const anniversaries = [
  Anniversary(name: '연애 시작', date: DateTime(2014, 4, 19)),
  Anniversary(name: '결혼', date: DateTime(2019, 4, 20)),
];

const birthdays = [
  Birthday(name: '기훈', birthDate: DateTime(1990, 6, 23), isLunar: false),
  Birthday(name: '수진', birthDate: DateTime(1993, 9, 23), isLunar: false),
  Birthday(name: '나린', birthDate: DateTime(2020, 6, 9), isLunar: false),
  Birthday(name: '나율', birthDate: DateTime(2022, 4, 15), isLunar: false),
  Birthday(name: '나준', birthDate: DateTime(2023, 12, 20), isLunar: false),
  Birthday(name: '일산할아버지', birthDate: DateTime(1961, 9, 19), isLunar: false),
  Birthday(name: '일산할머니', birthDate: DateTime(1962, 12, 21), isLunar: false),
  Birthday(name: '성길', birthDate: DateTime(1991, 5, 14), isLunar: false),
  Birthday(name: '예지', birthDate: DateTime(1993, 5, 20), isLunar: false),
  Birthday(name: '은하', birthDate: DateTime(2021, 1, 8), isLunar: false),
  Birthday(name: '은비', birthDate: DateTime(2022, 12, 12), isLunar: false),
  Birthday(name: '멋쟁이할아버지', birthDate: DateTime(1961, 7, 14), isLunar: true),
  Birthday(name: '콩콩콩할머니', birthDate: DateTime(1969, 7, 5), isLunar: true),
  Birthday(name: '영진', birthDate: DateTime(1997, 2, 18), isLunar: false),
  Birthday(name: '마루', birthDate: DateTime(2006, 11, 17), isLunar: false),
];

String formatDate(DateTime date) {
  return '${date.year}. ${date.month}. ${date.day}';
}

String buildBirthdayText(Birthday birthday, DateTime now) {
  final upcoming = findUpcomingBirthday(birthday, now);
  final lunarNote = birthday.isLunar
      ? ' (음 ${birthday.birthDate.month}. ${birthday.birthDate.day})'
      : '';
  final dDay = countDday(upcoming, now);
  return '${formatDate(upcoming)}$lunarNote · D - $dDay';
}

DateTime findUpcomingBirthday(Birthday birthday, DateTime now) {
  final currentYear = now.year;
  DateTime candidate = birthday.isLunar
      ? lunarToSolar(currentYear, birthday.birthDate.month, birthday.birthDate.day)
      : DateTime(currentYear, birthday.birthDate.month, birthday.birthDate.day);

  if (!isBeforeDay(candidate, now)) {
    return candidate;
  }

  final nextYear = currentYear + 1;
  return birthday.isLunar
      ? lunarToSolar(nextYear, birthday.birthDate.month, birthday.birthDate.day)
      : DateTime(nextYear, birthday.birthDate.month, birthday.birthDate.day);
}

DateTime lunarToSolar(int lunarYear, int lunarMonth, int lunarDay) {
  final calendar = KoreanLunarCalendar();
  calendar.setLunarDate(lunarYear, lunarMonth, lunarDay, false);
  return DateTime(calendar.solarYear, calendar.solarMonth, calendar.solarDay);
}

int countDday(DateTime target, DateTime now) {
  return stripTime(target).difference(stripTime(now)).inDays;
}

int countDaysIncludeStart(DateTime start, DateTime now) {
  return stripTime(now).difference(stripTime(start)).inDays + 1;
}

bool isBeforeDay(DateTime date, DateTime reference) {
  return stripTime(date).isBefore(stripTime(reference));
}

DateTime stripTime(DateTime date) {
  return DateTime(date.year, date.month, date.day);
}
