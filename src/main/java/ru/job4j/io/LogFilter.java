package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            input.lines().forEach(line -> {
                if ("404".equals(line.split(" ")[line.split(" ").length - 2])) {
                    list.add(line);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
