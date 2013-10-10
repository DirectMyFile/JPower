package jpower.core;

public class ReleaseInfo {
    private static String VERSION = "%%JPOWER_VERSION%%";

    public static String getVersion() {
        return VERSION;
    }

    public static String[] getVersionMetadata() {
        return VERSION.split("\\.");
    }
}
