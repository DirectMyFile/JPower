package jpower.core;

/**
 * Operating System Utilities
 */
final class OperatingSystem {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    private static boolean isWindows() {
        return OS.contains("win");
    }

    private static boolean isMac() {
        return OS.contains("mac");
    }

    private static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    private static boolean isSolaris() {
        //noinspection SpellCheckingInspection
        return OS.contains("sunos");
    }

    public static boolean isUnknown() {
        return !(isUnix() || isSolaris() || isMac() || isWindows());
    }

    public static boolean isCaseSensitive() {
        return isUnix();
    }
}