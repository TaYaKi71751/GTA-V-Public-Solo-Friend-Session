package gtav;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Path {
	public static File getUpperPath(File path){
		System.out.println(path.toString());
		// https://stackoverflow.com/questions/10336293/splitting-filenames-using-system-file-separator-symbol
		String[] psa = path.toString().split(Pattern.quote(System.getProperty("file.separator")));
		if(psa.length > 0) psa[psa.length - 1] = null;
		// https://stackoverflow.com/questions/24112715/java-8-filter-array-using-lambda
		psa = Arrays.stream(psa).filter(p -> p != null).toArray(String[]::new);
		// https://www.baeldung.com/java-file-vs-file-path-separator
		return new File(String.join(File.separator, psa));
	}
}
