import 'package:flutter/material.dart';

import '../data/anniversaries.dart';
import '../data/birthdays.dart';
import '../models/birthday_entry.dart';
import '../utils/date_helpers.dart';

const _backgroundColor = Color(0xFFFFD9EC);
const _textColor = Color(0xFF333333);

class MainPage extends StatelessWidget {
  const MainPage({super.key});

  @override
  Widget build(BuildContext context) {
    final entries = birthdaysForGroup(BirthdayGroup.main);

    return BirthdayScaffold(
      headerAsset: 'assets/images/love.png',
      heroAsset: 'assets/images/family.jpg',
      heroHeight: 300,
      children: [
        AnniversaryLine(anniversary: loveAnniversary),
        AnniversaryLine(anniversary: weddingAnniversary),
        const SizedBox(height: 8),
        ...entries.map((entry) => BirthdayLine(entry: entry)),
      ],
    );
  }
}

class HusbandPage extends StatelessWidget {
  const HusbandPage({super.key});

  @override
  Widget build(BuildContext context) {
    final entries = birthdaysForGroup(BirthdayGroup.husband);

    return BirthdayScaffold(
      headerAsset: 'assets/images/love_hus.png',
      heroAsset: 'assets/images/osori_hus.jpeg',
      heroHeight: 350,
      children: entries.map((entry) => BirthdayLine(entry: entry)).toList(),
    );
  }
}

class WifePage extends StatelessWidget {
  const WifePage({super.key});

  @override
  Widget build(BuildContext context) {
    final entries = birthdaysForGroup(BirthdayGroup.wife);

    return BirthdayScaffold(
      headerAsset: 'assets/images/love_wife.png',
      heroAsset: 'assets/images/osori_wife.png',
      heroHeight: 350,
      children: entries.map((entry) => BirthdayLine(entry: entry)).toList(),
    );
  }
}

class BirthdayScaffold extends StatelessWidget {
  final String headerAsset;
  final String heroAsset;
  final double heroHeight;
  final List<Widget> children;

  const BirthdayScaffold({
    super.key,
    required this.headerAsset,
    required this.heroAsset,
    required this.heroHeight,
    required this.children,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      color: _backgroundColor,
      child: SafeArea(
        child: SingleChildScrollView(
          child: Column(
            children: [
              const SizedBox(height: 12),
              Image.asset(
                headerAsset,
                height: 60,
                fit: BoxFit.contain,
              ),
              const SizedBox(height: 12),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(24),
                  child: Image.asset(
                    heroAsset,
                    height: heroHeight,
                    width: double.infinity,
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              const SizedBox(height: 12),
              ...children,
              const SizedBox(height: 20),
            ],
          ),
        ),
      ),
    );
  }
}

class AnniversaryLine extends StatelessWidget {
  final Anniversary anniversary;

  const AnniversaryLine({super.key, required this.anniversary});

  @override
  Widget build(BuildContext context) {
    final days = countDaysIncludeStandardFrom(anniversary.date, DateTime.now());
    final text =
        '${anniversary.label} - ${formatReadableDate(anniversary.date)}.  D + $days';

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4, horizontal: 12),
      child: Text(
        text,
        textAlign: TextAlign.center,
        style: const TextStyle(color: _textColor, fontSize: 16),
      ),
    );
  }
}

class BirthdayLine extends StatelessWidget {
  final BirthdayEntry entry;

  const BirthdayLine({super.key, required this.entry});

  @override
  Widget build(BuildContext context) {
    final upcoming = upcomingBirthday(entry.birth, entry.isLunar);
    final days = countDdayFrom(upcoming, DateTime.now());
    final dateText = formatReadableDate(upcoming);
    final lunarText = entry.isLunar
        ? ' (음 ${entry.birth.month}. ${entry.birth.day})'
        : '';
    final text = '${entry.name}  $dateText$lunarText.    D - $days';

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4, horizontal: 12),
      child: Text(
        text,
        textAlign: TextAlign.center,
        style: const TextStyle(color: _textColor, fontSize: 16),
      ),
    );
  }
}
