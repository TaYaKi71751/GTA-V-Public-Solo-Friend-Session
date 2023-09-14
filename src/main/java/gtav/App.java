package gtav;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) throws IOException{
		// TODO TEST THIS ON WINDOWS
		// TODO Add GUI with javax.swing
		Find find = new Find();
		ArrayList<File> files = find.findFile("explorer.exe");
		for(File file:files){
			System.out.println(file);
		}
	}
}
