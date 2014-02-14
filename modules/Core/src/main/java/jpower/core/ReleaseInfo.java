package jpower.core;

import jpower.core.utils.IOUtils;

/**
 * JPower Release Information
 */
final class ReleaseInfo {
    private static String VERSION;

    private static String getVersion() {
        if (VERSION == null) {
            String v = IOUtils.getResourceAsString(ReleaseInfo.class, "release");
            if (v == null)
                VERSION = "UNKNOWN";
            else
                VERSION = v;
        }
        return VERSION;
    }

    public static String[] getVersionMetadata() {
        return getVersion().split("\\.");
    }

    public static void main(String[] args) {
        System.out.println("JPower v" + getVersion());
    }
}