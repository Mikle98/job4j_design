package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String key;
        String value;
        for (String arg : args) {
            key = arg.substring(1, arg.indexOf('='));
            value = arg.substring(arg.indexOf('=') + 1);
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (arg.split("=").length < 2) {
                if (arg.indexOf('=') == -1) {
                    throw new IllegalArgumentException(String.format("Error: This argument '-request?msgHello' does not contain an equal sign", arg));
                }
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
            }
            if (arg.split("=")[0] != null && !arg.split("=")[0].matches("-.+")) {
                if (arg.indexOf('-') != 0) {
                    throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
                }
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
            }
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
