package gtav;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Find {
	public ArrayList<File> findFile(String filename) throws IOException {
		Drive drives = new Drive();
		File[] drive_paths = drives.getDriveLists();
		ArrayList<File> result_paths = new ArrayList<>();
		for(File drive_path:drive_paths){
			// https://stackoverflow.com/questions/15464111/run-cmd-commands-through-java
			// https://learn.microsoft.com/en-us/windows-server/administration/windows-commands/findstr
			ProcessBuilder builder = new ProcessBuilder(
					"cmd.exe", "/c", "findstr " + drive_path + " /c:\'" + filename + "\'"
			);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) { break; }
				if(line.endsWith(filename)){
					File result_path = new File(line);
					result_paths.add(result_path);
					System.out.println(result_path);
				}
			}
		}
		return result_paths;
	}
}
