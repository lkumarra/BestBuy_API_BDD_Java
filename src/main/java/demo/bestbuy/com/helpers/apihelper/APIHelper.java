package demo.bestbuy.com.helpers.apihelper;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * This class contains the methods to start and stop API Server
 *
 * @author Lavendra Kumar Rajput
 * @Date 2 July 2021
 */
@Slf4j
public final class APIHelper {

    private static Process process = null;

    /**
     * Start the API Server
     *
     * @author Lavendra rajput
     */
    public static void startApiServer() {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "cd /Users/lkumarrajput/Developer/Code/Node/api-playground && npm start");
        processBuilder.redirectErrorStream(true);
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            log.error("Error while starting the server with error message : {} ", e.getMessage());
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
