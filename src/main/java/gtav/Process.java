package gtav;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.SystemUtils;

public class Process {
	public void kill(String process) throws IOException{
		
		ProcessBuilder builder = null;
		if(SystemUtils.IS_OS_WINDOWS) {
			builder = new ProcessBuilder(
				"cmd.exe", "/c", "taskkill.exe", "/F", "/IM", process
			);
		}
		if(SystemUtils.IS_OS_LINUX) {
			builder = new ProcessBuilder(
				"bash", "-c", "sudo pkill " + process + "|| true"
			);
		}
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
