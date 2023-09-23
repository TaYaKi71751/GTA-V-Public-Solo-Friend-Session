package gtav;

import java.io.File;
import java.util.Arrays;

public class Path {
	public static File getUpperPath(File path){
		String[] psa = (path + "").split(File.separator);
		if(psa.length > 0) psa[psa.length - 1] = null;
		// https://stackoverflow.com/questions/24112715/java-8-filter-array-using-lambda
		psa = Arrays.stream(psa).filter(p -> p != null).toArray(String[]::new);
		// https://www.baeldung.com/java-file-vs-file-path-separator
		return new File(String.join(File.separator, psa));
	}
}
