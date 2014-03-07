package jpower.gcp;

import jpower.core.utils.StringUtils;
import jpower.socket.Client;
import jpower.socket.ClientFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class GCP {
    public static final String COLON = "\u0001";
    public static final String EQUALS = "\u0002";
    private static final Pattern EQUALS_PATTERN = Pattern.compile(EQUALS);


    private Client client;

    public GCP() {
    }

    public void connect(String host, int port) {
        client = ClientFactory.create(host, port);
    }

    public static GCPLine parse(String input) {
        List<String> parts = StringUtils.tokenize(input, COLON);
        if (parts.size() <= 1) {
            throw new RuntimeException(new LineParseException("Invalid GCP Line: " + input));
        }
        String command = parts.remove(0);
        Map<String, String> opts = new HashMap<>();
        parts.forEach(part -> {
            String[] keyValue = EQUALS_PATTERN.split(part, 2);
            if (keyValue.length != 2) {
                throw new RuntimeException(new LineParseException("Invalid GCP Line: " + input));
            }
            opts.put(keyValue[0], keyValue[1]);
        });
        return new GCPLine(command, opts);
    }

    public void send(GCPLine line) {
        client.writeLine(line.toString());
    }
}
