package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean write = true;
        List<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            reader.lines().forEach(list::add);
            for (String str : list) {
                if (write && ("400".equals(str.split(" ")[0])
                        || "500".equals(str.split(" ")[0]))) {
                    writer.write(str.split(" ")[1] + ";");
                    write = false;
                }
                if (!write && ("200".equals(str.split(" ")[0])
                        || "300".equals(str.split(" ")[0]))) {
                    writer.write(str.split(" ")[1] + ";\n");
                    write = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
