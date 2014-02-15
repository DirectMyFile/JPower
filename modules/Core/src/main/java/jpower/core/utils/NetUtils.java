package jpower.core.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtils {
    /**
     * Checks if the Host is Reachable
     *
     * @param host Hostname
     * @return is reachable
     */
    public static boolean ping(String host) {
        try {
            return InetAddress.getByName(host).isReachable(100);
        } catch (IOException e) {
            return false;
        }
    }

    public static HttpURLConnection getConnection(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }
}
