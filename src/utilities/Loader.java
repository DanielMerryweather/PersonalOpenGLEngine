package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

	public static String loadAsString(String s) {
		String result = "";
		Scanner sr = null;
		try {
			sr = new Scanner(new File(s));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(sr.hasNextLine()) {
			result += sr.nextLine() + "\n";
		}
		return result;
	}
	
}
