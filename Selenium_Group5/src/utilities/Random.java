package utilities;

import java.util.Date;

public class Random {
	public static String getArticleName() {
		String str =  "Group 5 article " + new Date().getTime();
		return str;
	}
}
