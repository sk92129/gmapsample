import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class AppConfig {
  static const String _configPath = 'lib/dev.json';
  static Map<String, dynamic>? _config;

  static Future<void> initialize() async {
    try {
      final String jsonString = await rootBundle.loadString(_configPath);
      _config = json.decode(jsonString) as Map<String, dynamic>;
    } catch (e) {
      debugPrint('Error loading config: $e');
      _config = {};
    }
  }

  static String get gmapApiKey {
    return _config?['GMAP_API_KEY'] ?? '';
  }

  static bool get isInitialized => _config != null;
}
