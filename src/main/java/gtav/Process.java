package gtav;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Process {
	public void kill(String process) throws IOException{
		ProcessBuilder builder = new ProcessBuilder(
			"cmd.exe", "/c", "taskkill.exe", "/F", "/IM", process
		);
		builder.redirectErrorStream(true);
		java.lang.Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) { break; }
			System.out.println(line);
		}
	}
}
