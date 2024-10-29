import 'dart:convert';
import 'dart:io';

class StartupMeta {
	static List<int> getBytes(String unique_session_code) {
		List<int> result = [];
		List<String> prefix = """<?xml version="1.0" encoding="UTF-8"?>
<CDataFileMgr__ContentsOfDataFileXml>
 <disabledFiles />
 <includedXmlFiles itemType="CDataFileMgr__DataFileArray" />
 <includedDataFiles />
 <dataFiles itemType="CDataFileMgr__DataFile">
  <Item>
   <filename>platform:/data/cdimages/scaleform_platform_pc.rpf</filename>
   <fileType>RPF_FILE</fileType>
  </Item>
  <Item>
   <filename>platform:/data/cdimages/scaleform_frontend.rpf</filename>
   <fileType>RPF_FILE_PRE_INSTALL</fileType>
  </Item>
 </dataFiles>
 <contentChangeSets itemType="CDataFileMgr__ContentChangeSet" />
 <patchFiles />
</CDataFileMgr__ContentsOfDataFileXml>"""
  .split('\n');
		for (String line in prefix) {
			result.addAll(utf8.encode(line));
			result.add(0x0d);
		}
		result.addAll(utf8.encode('<!-- $unique_session_code -->'));
		result.add(0x0d);
		result.add(0x0a);
		return result;
 }

 static Future<void> apply(List<Directory> game_directories, String unique_session_code) async {
		for (Directory game_directory in game_directories) {
			File file;
			if (Platform.isWindows) {
				file = File(game_directory.path + '\\x64\\data\\startup.meta');
			} else if (Platform.isLinux || Platform.isMacOS) {
				file = File(game_directory.path + '/x64/data/startup.meta');
			} else {
				throw Error();
			}
			await file.writeAsBytes(getBytes(unique_session_code));
		}
	}

 static Future<void> delete(List<Directory> game_directories) async {
		for (Directory game_directory in game_directories) {
			File file;
			if (Platform.isWindows) {
				file = File(game_directory.path + '\\x64\\data\\startup.meta');
			} else if (Platform.isLinux || Platform.isMacOS) {
				file = File(game_directory.path + '/x64/data/startup.meta');
			} else {
				throw Error();
			}
			await file.delete();
		}
	}
}
