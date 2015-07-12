package utilities;

import java.util.Date;

public class Random {
	public static String getRandomName() {
		String str =  "Group 5 " + new Date().getTime();
		return str;
	}
}
