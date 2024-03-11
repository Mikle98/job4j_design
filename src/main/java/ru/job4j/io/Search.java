package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validArgs(args);
        Path start = Paths.get(".");
        System.out.println(search(start, path -> !path.toFile().getName().endsWith(".js")));
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Need two arguments");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("First argument must be a directory");
        }
        if (!args[1].matches("\\..+")) {
            throw new IllegalArgumentException("Second argument must be a file extension");
        }
    }
}
