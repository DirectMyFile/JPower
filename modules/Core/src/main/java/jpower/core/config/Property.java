package jpower.core.config;

import java.util.LinkedList;
import java.util.List;

public class Property {
    private final List<String> comments = new LinkedList<>();
    private final String key;
    private String value;

    public Property(String key) {
        this.key = key;
    }

    public void set(String value) {
        this.value = value;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public List<String> comments() {
        return comments;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        comments.forEach(comment -> {
            builder.append('#').append(' ').append(comment).append(System.lineSeparator());
        });
        builder.append(key).append(':').append(' ').append(value).append(System.lineSeparator());
        return builder.toString();
    }

    public List<String> toLines() {
        List<String> lines = new LinkedList<>();
        comments.forEach(comment -> lines.add("# " + comment));
        lines.add(key + ": " + value);
        return lines;
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }
 }
