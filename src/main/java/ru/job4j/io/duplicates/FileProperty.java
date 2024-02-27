package ru.job4j.io.duplicates;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

public class FileProperty extends SimpleFileVisitor<Path> {
    private long size;

    private String name;

    private Map<FileProperty, Path> setPath = new HashMap<>();

    private Set<String> duplFile = new TreeSet<>();

    final private Predicate<BasicFileAttributes> condition = BasicFileAttributes::isRegularFile;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(attrs)) {
            FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
            if (setPath.containsKey(fileProperty)) {
                duplFile.add(String.format("%s - %d%n", setPath.get(fileProperty), fileProperty.getSize()));
                duplFile.add(String.format("%s - %d%n", file.toAbsolutePath(), attrs.size()));
            }
            setPath.put(fileProperty, file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public String getDupl() {
        return duplFile.toString();
    }
}
