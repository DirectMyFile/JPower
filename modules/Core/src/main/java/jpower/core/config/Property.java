package jpower.core.config;

import java.util.Collection;
import java.util.LinkedList;

public class Property {
    private final Collection<String> comments = new LinkedList<>();
    private final String key;
    private String value;

    public Property(String key) {
        this.key = key;
    }

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public Collection<String> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        comments.forEach(comment -> {
            builder.append(comment).append('\n');
        });
        builder.append(key).append(':').append(' ').append(value).append('\n');
        return builder.toString();
    }

    public Collection<String> toLines() {
        Collection<String> lines = new LinkedList<>();
        comments.forEach(comment -> lines.add("# " + comment));
        lines.add(key + ": " + value);
        return lines;
    }

    public String key() {
        return key;
    }
 }
