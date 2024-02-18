package ru.job4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .filter(line -> line.matches("[^#]+"))
                    .forEach(line -> {
                        if (line.split("=")[0].isEmpty()
                                || line.substring(line.indexOf('=')).isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        values.put(line.split("=")[0], line.substring(line.indexOf('=') + 1));
                        System.out.println(line);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Config c = new Config("./data/app.properties");
        c.load();
        System.out.println(c.values.size());
    }
}
