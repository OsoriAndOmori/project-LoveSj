import '../models/birthday_entry.dart';

final List<BirthdayEntry> birthdays = [
  BirthdayEntry(
    name: '기훈',
    birth: DateTime(1990, 6, 23),
    isLunar: false,
    group: BirthdayGroup.main,
  ),
  BirthdayEntry(
    name: '수진',
    birth: DateTime(1993, 9, 23),
    isLunar: false,
    group: BirthdayGroup.main,
  ),
  BirthdayEntry(
    name: '나린',
    birth: DateTime(2020, 6, 9),
    isLunar: false,
    group: BirthdayGroup.main,
  ),
  BirthdayEntry(
    name: '나율',
    birth: DateTime(2022, 4, 15),
    isLunar: false,
    group: BirthdayGroup.main,
  ),
  BirthdayEntry(
    name: '나준',
    birth: DateTime(2023, 12, 20),
    isLunar: false,
    group: BirthdayGroup.main,
  ),
  BirthdayEntry(
    name: '일산할아버지',
    birth: DateTime(1961, 9, 19),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '일산할머니',
    birth: DateTime(1962, 12, 21),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '성길',
    birth: DateTime(1991, 5, 14),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '예지',
    birth: DateTime(1993, 5, 20),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '은하',
    birth: DateTime(2021, 1, 8),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '은비',
    birth: DateTime(2022, 12, 12),
    isLunar: false,
    group: BirthdayGroup.husband,
  ),
  BirthdayEntry(
    name: '멋쟁이할아버지',
    birth: DateTime(1961, 7, 14),
    isLunar: true,
    group: BirthdayGroup.wife,
  ),
  BirthdayEntry(
    name: '콩콩콩할머니',
    birth: DateTime(1969, 7, 5),
    isLunar: true,
    group: BirthdayGroup.wife,
  ),
  BirthdayEntry(
    name: '영진',
    birth: DateTime(1997, 2, 18),
    isLunar: false,
    group: BirthdayGroup.wife,
  ),
  BirthdayEntry(
    name: '마루',
    birth: DateTime(2006, 11, 17),
    isLunar: false,
    group: BirthdayGroup.wife,
  ),
];

List<BirthdayEntry> birthdaysForGroup(BirthdayGroup group) {
  return birthdays.where((entry) => entry.group == group).toList();
}
