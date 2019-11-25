package servlet.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import servlet.contant.AppConstant;

public class AppUtil {
	
	public static String myPrice; 
	public static String mycatagory; 
	private static final long LIMIT = 10000000000L;
	private static long last = 0;

	public static long getID() {
	  // 10 digits.
	  long id = System.currentTimeMillis() % LIMIT;
	  if ( id <= last ) {
	    id = (last + 1) % LIMIT;
	  }
	  return last = id;
	}
	
	
	public static void createFile(String textline) {
		File file = new File(AppConstant.IOS_DIR_WHERE_JAVA_APPICATION_GET_DATA);
		// Create the file
		try {
			file.createNewFile();
			// Write Content
			FileWriter writer = null;
			writer = new FileWriter(file);
			if (textline.contains("qqqqqStart")) {
				writer.write("Start");
			}else if(textline.contains("qqqqqarunkhkDisconnected")) {
				writer.write("Disconnected");
			}else {
				String last10 = ""; // substring containing last 10 characters
				if (textline.length() > 13) {
					last10 = textline.substring(textline.length() - 10);
				} else {
					last10 = textline;
				}
				System.out.println("last 10 char " + last10);
				writer.write(last10);
			}

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
