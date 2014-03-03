package jpower.gcp;

import jpower.core.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GCP {
    public static GCPLine parse(String input) {
        List<String> parts = StringUtils.tokenize(input, ":");
        if (parts.size() <= 1) {
            throw new RuntimeException(new LineParseException("Invalid GCP Line: " + input));
        }
        String command = parts.remove(0);
        Map<String, String> opts = new HashMap<>();
        parts.forEach(part -> {
            String[] keyValue = part.split(part, 2);
            if (keyValue.length != 2) {
                throw new RuntimeException(new LineParseException("Invalid GCP Line: " + input));
            }
            opts.put(keyValue[0], keyValue[1]);
        });
        return new GCPLine(command, opts);
    }
}
