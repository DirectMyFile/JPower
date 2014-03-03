package jpower.gcp;

import java.util.Map;

public class GCP {
    public Line parse(String input) {
        return null;
    }

    static class Line {
        private String command;
        private Map<String, String> opts;

        public Line(String command, Map<String, String> opts) {
            this.command = command;
            this.opts = opts;
        }

        public String command() {
            return command;
        }

        public Map<String, String> opts() {
            return opts;
        }
    }
}
