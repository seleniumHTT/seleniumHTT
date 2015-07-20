package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Random {
	public static String getCurrentTime() {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
		 Date date = new Date();
		 return dateFormat.format(date);
	}
}
