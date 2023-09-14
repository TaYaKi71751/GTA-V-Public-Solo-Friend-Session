package gtav;

import java.io.File;

public class Drive {
	public File[] getDriveLists(){
		// https://stackoverflow.com/questions/3542018/how-can-i-get-list-of-all-drives-but-also-get-the-corresponding-drive-type-remo
		File[] paths;
		paths = File.listRoots();
		return paths;
	}
}
