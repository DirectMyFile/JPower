package jpower.gcp;

import java.util.Map;

public class GCPLine {
    private final String command;
    private final Map<String, String> opts;

    public GCPLine(String command, Map<String, String> opts) {
        this.command = command;
        this.opts = opts;
    }

    public String command() {
        return command;
    }

    public Map<String, String> opts() {
        return opts;
    }

    @SuppressWarnings("HardcodedLineSeparator")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(command()).append(GCP.COLON);
        opts().keySet().forEach(key -> {
            builder.append(GCP.COLON).append(key).append(GCP.EQUALS).append(opts().get(key));
        });

        return builder.toString();
    }
}
