package jpower.core.utils;

import java.io.IOException;
import java.net.InetAddress;

public final class NetUtils {
    /**
     * Checks if the Host is Reachable
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
}
