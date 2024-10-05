import 'dart:io';

class Task {
  static Future<void> kill(String process) async {
    if (Platform.isWindows) {
      Process.run('taskkill.exe', ['/F', '/IM', '$process']);
    } else if (Platform.isLinux || Platform.isMacOS) {
      Process.run('pkill', ['-9', '$process']);
    }
  }
}
