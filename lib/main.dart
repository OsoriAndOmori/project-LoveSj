import 'package:flutter/material.dart';
import 'package:home_widget/home_widget.dart';

import 'services/notification_service.dart';
import 'services/widget_service.dart';
import 'ui/pages.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  HomeWidget.registerInteractivityCallback(backgroundCallback);
  await NotificationService.initialize();
  await NotificationService.scheduleBirthdayNotifications();
  await WidgetService.updateWidget();

  runApp(const LoveSjApp());
}

@pragma('vm:entry-point')
Future<void> backgroundCallback(Uri? uri) async {
  if (uri?.host == 'refresh') {
    await WidgetService.refreshWidget();
  }
}

class LoveSjApp extends StatelessWidget {
  const LoveSjApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Love SJ',
      debugShowCheckedModeBanner: false,
      home: const HomePager(),
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFFD81B60)),
        useMaterial3: true,
      ),
    );
  }
}

class HomePager extends StatefulWidget {
  const HomePager({super.key});

  @override
  State<HomePager> createState() => _HomePagerState();
}

class _HomePagerState extends State<HomePager> {
  final PageController _controller = PageController(initialPage: 1);

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: _controller,
        children: const [
          HusbandPage(),
          MainPage(),
          WifePage(),
        ],
      ),
    );
  }
}
