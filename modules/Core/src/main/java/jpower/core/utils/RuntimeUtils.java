package jpower.core.utils;

import java.io.File;
import java.io.IOException;

public class RuntimeUtils {
    public static void exit(int code) {
        Runtime.getRuntime().exit(code);
        ThreadUtils.sleep(500);
        Runtime.getRuntime().halt(code);
    }

    public static Process execute(String[] command, File directory, String[] env) throws IOException {
        return Runtime.getRuntime().exec(command, env, directory);
    }
}
