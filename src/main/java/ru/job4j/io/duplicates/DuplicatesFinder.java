package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        FileProperty fileProperty = new FileProperty(0L, "");
        Files.walkFileTree(Path.of("./"), fileProperty);
        System.out.println(fileProperty.getDupl());
    }
}
