package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class Analysis {
    public void unavailable(String source, String target) {
        Predicate condition = new Predicate() {
            boolean writeStart = true;
            boolean writeEnd = false;
            boolean write;
            @Override
            public boolean test(Object o) {
                write = false;
                if (writeStart && ("400".equals(o) || "500".equals(o))) {
                    writeStart = false;
                    writeEnd = true;
                    write = true;
                }
                if (writeEnd && ("200".equals(o) || "300".equals(o))) {
                    writeEnd = false;
                    writeStart = true;
                    write = true;
                }
                return write;
            }
        };
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            reader.lines()
                    .filter(line -> condition.test(line.split(" ")[0]))
                    .forEach(line -> {
                                try {
                                    writer.write(line.split(" ")[1] + ";");
                                    if ("200".equals(line.split(" ")[0])
                                        || "300".equals(line.split(" ")[0])) {
                                        writer.write("\n");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
