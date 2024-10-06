import 'dart:async';
import 'dart:io';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:gtav_session/find.dart';
import 'package:gtav_session/process.dart';
import 'package:gtav_session/startup_meta.dart';

import 'package:rsa_encrypt/rsa_encrypt.dart';
import 'package:pointycastle/asymmetric/api.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Public Solo Session',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Public Solo Session'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final passcode = TextEditingController();
  String _status = '';

  Future<void> _random() async {
    var helper = RsaKeyHelper();
    var keypair = await helper.computeRSAKeyPair(helper.getSecureRandom());
    keypair.privateKey;
    passcode.text = helper
        .encodePrivateKeyToPemPKCS1(keypair.privateKey as RSAPrivateKey)
        .replaceAll('\n', '');
  }

  Future<void> _kill() async {
    List<String> processList = 'PlayGTAV.exe,GTA5.exe'.split(',');
    for (String process in processList) {
      setState(() {
        _status = 'Killing $process';
      });
      await Task.kill(process);
      setState(() {
        _status = 'Killed $process';
      });
    }
  }

  Future<void> _apply() async {
    setState(() {
      _status = 'Finding Game Paths';
    });
    List<File> game_paths = (await Find.file('PlayGTAV.exe'));
    List<Directory> game_directories = List.empty(growable: true);
    for (File game_path in game_paths) {
      game_directories.add(game_path.parent);
    }
    setState(() {
      _status = 'Applying';
    });
    await StartupMeta.apply(game_directories, passcode.text);
    await _kill();

    setState(() {
      _status = 'Applied';
    });
  }

  Future<void> _delete() async {
    setState(() {
      _status = 'Finding Game Paths';
    });
    List<File> game_paths = (await Find.file('PlayGTAV.exe'));
    List<Directory> game_directories = List.empty(growable: true);
    for (File game_path in game_paths) {
      game_directories.add(game_path.parent);
    }
    setState(() {
      _status = 'Deleting';
    });
    await StartupMeta.delete(game_directories);
    await _kill();

    setState(() {
      _status = 'Deleted';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                '$_status',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              SizedBox(
                width: 250,
                child: TextField(
                  controller: passcode,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'PassCode',
                  ),
                ),
              ),
            ],
          ),
        ),
        floatingActionButton: Wrap(
          children: [
            FloatingActionButton(
              onPressed: _random,
              tooltip: 'Random',
              child: const Icon(Icons.shuffle),
            ),
            FloatingActionButton(
              onPressed: _kill,
              tooltip: 'Kill',
              child: const Icon(Icons.close),
            ),
            FloatingActionButton(
              onPressed: _apply,
              tooltip: 'Apply',
              child: const Icon(Icons.check),
            ),
            FloatingActionButton(
              onPressed: _delete,
              tooltip: 'Delete',
              child: const Icon(Icons.delete),
            ),
          ],
        ));
  }
}
