
package utility;

import java.io.File;

/**
 *  Framework Constants should defined here.
 *  
 * Example :APK_PATH,Default Timeout

 * @author Shiv Jirwankar
 *
 */

public class Constant {

	public static final String APK_PATH =  new File("").getAbsolutePath()+File.separator+"app"+File.separator+"Amazon_shopping.apk";
	public static final int implicitWait=10;
	public static final int shortTimeout=20;	
	public static final int LongTimeout=60;	
}
