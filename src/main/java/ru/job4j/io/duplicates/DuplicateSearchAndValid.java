package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class DuplicateSearchAndValid extends SimpleFileVisitor<Path> {
    private Predicate<BasicFileAttributes> condition = BasicFileAttributes::isRegularFile;
    private Set<String> duplFile = new TreeSet<>();
    private Map<FileProperty, Path> setPath = new HashMap<>();

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
