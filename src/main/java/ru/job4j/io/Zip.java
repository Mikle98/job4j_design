package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target, true)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void paramsValid(String[] args, ArgsName standartValid) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!Arrays.asList("-d", "-e", "-o").contains(arg.split("=")[0])) {
                throw new IllegalArgumentException("Basic arguments not found");
            }
        }
        if (!Files.readAttributes(Path.of(standartValid.get("d")), BasicFileAttributes.class).isDirectory()) {
            throw new IllegalArgumentException("Key -d is not Directory");
        }
        if (!standartValid.get("e").matches("\\..+")) {
            throw new IllegalArgumentException("Second argument must be a file extension");
        }
        if (!standartValid.get("o").matches(".+\\.zip")) {
            throw new IllegalArgumentException("The archive name must have the extension \".zip\"");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        paramsValid(args, argsName);
        packFiles(Search.search(Path.of(argsName.get("d")),
                                path -> !path.toFile().getName().endsWith(argsName.get("e"))),
                                new File(argsName.get("o")));
    }
}
