package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String scan;
        String delimiter = argsName.get("delimiter");
        StringBuilder rsl = new StringBuilder();
        List<Integer> index = new ArrayList<>();
        List<String> filter = List.of(argsName.get("filter").split(","));
        try (var scanner = new Scanner(new BufferedReader(new FileReader(argsName.get("path"),
                Charset.forName("WINDOWS-1251"))))
                .useDelimiter(delimiter)) {
            while (scanner.hasNext()) {
                scan = scanner.nextLine();
                for (int j = 0; j < filter.size(); j++) {
                    for (int i = 0; i < scan.split(delimiter).length; i++) {
                        if (filter.get(j).equals(scan.split(delimiter)[i])) {
                            index.add(i);
                        }
                    }
                    rsl.append(String.format("%s%s", scan.split(delimiter)[index.get(j)],
                            delimiter));
                }
                rsl.deleteCharAt(rsl.length() - 1);
                rsl.append(System.lineSeparator());
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(rsl);
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
                    writer.write(String.valueOf(rsl));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validParams(args, argsName);
        handle(argsName);
    }

    private static void validParams(String[] args, ArgsName argsName) throws IOException {
        String delimiter = argsName.get("delimiter");
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!Arrays.asList("-path", "-delimiter", "-out", "-filter").contains(arg.split("=")[0])) {
                throw new IllegalArgumentException("Basic arguments not found");
            }
        }
        if (!argsName.get("path").matches(".+\\.csv")) {
            throw new IllegalArgumentException("The archive name must have the extension \".csv\"");
        }
        if (!delimiter.matches(";") && !delimiter.matches(",")) {
            throw new IllegalArgumentException("The delimiter must be ;");
        }
    }
}
