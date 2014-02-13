package jpower.core;

/**
 * Operating System Utilities
 */
public class OperatingSystem {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public static boolean isSolaris() {
        return OS.contains("sunos");
    }

    public static boolean isUnknown() {
        return !(isUnix() || isSolaris() || isMac() || isWindows());
    }

    public static boolean isCaseSensitive() {
        return isUnix();
    }
}