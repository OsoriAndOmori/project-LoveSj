class Anniversary {
  final String label;
  final DateTime date;

  Anniversary({required this.label, required this.date});
}

final Anniversary loveAnniversary = Anniversary(
  label: '연애 시작한 날',
  date: DateTime(2014, 4, 19),
);

final Anniversary weddingAnniversary = Anniversary(
  label: '결혼한 날',
  date: DateTime(2019, 4, 20),
);
