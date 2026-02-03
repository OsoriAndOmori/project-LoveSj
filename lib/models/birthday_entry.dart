enum BirthdayGroup { main, husband, wife }

class BirthdayEntry {
  final String name;
  final DateTime birth;
  final bool isLunar;
  final BirthdayGroup group;

  const BirthdayEntry({
    required this.name,
    required this.birth,
    required this.isLunar,
    required this.group,
  });
}
