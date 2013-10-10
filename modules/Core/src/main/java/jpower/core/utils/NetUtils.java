package jpower.core.utils;

import java.io.IOException;
import java.net.InetAddress;

public class NetUtils {
    public static boolean ping(String host) {
        try {
            return InetAddress.getByName(host).isReachable(100);
        } catch (IOException e) {
            return false;
        }
    }
}
