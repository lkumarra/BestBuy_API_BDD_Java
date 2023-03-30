package demo.bestbuy.com.helpers.apihelper;

import java.io.IOException;

/**
 * This class contains the methods to start and stop API Server
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public final class APIHelper {

	private static Process process = null;

	/**
	 * Start the API Server
	 * 
	 * @author Lavendra rajput
	 */
	public static void startApiServer() {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c",
				"E: && cd E:\\NodeJsTestingProjects\\BestBuy API\\api-playground && npm start");
		processBuilder.redirectErrorStream(true);
		try {
			process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop the API Server
	 * 
	 * @author Lavendra rajput
	 */
	public static void stopApiServer() {
		if (process != null) {
			process.destroyForcibly();
		}
	}
}
