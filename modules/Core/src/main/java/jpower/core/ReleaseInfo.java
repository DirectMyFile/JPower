package jpower.core;

import jpower.core.utils.IOUtils;

import java.util.regex.Pattern;

/**
 * JPower Release Information
 */
final class ReleaseInfo {
    private static final Pattern SPLIT_BY_DOT = Pattern.compile("\\.");
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
        return SPLIT_BY_DOT.split(getVersion());
    }

    public static void main(String[] args) {
        System.out.println("JPower v" + getVersion());
    }
}