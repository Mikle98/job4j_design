package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String scan;
        StringBuilder rsl = new StringBuilder();
        List<Integer> index = new ArrayList<>();
        List<String> filter = List.of(argsName.get("filter").split(","));
        try (var scanner = new Scanner(new BufferedReader(new FileReader(argsName.get("path"),
                Charset.forName("WINDOWS-1251"))))
                .useDelimiter(argsName.get("delimiter"))) {
            while (scanner.hasNext()) {
                scan = scanner.nextLine();
                for (int j = 0; j < filter.size(); j++) {
                    for (int i = 0; i < scan.split(argsName.get("delimiter")).length; i++) {
                        if (filter.get(j).equals(scan.split(argsName.get("delimiter"))[i])) {
                            index.add(i);
                        }
                    }
                    rsl.append(String.format("%s%s", scan.split(argsName.get("delimiter"))[index.get(j)],
                            argsName.get("delimiter")));
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
        if (!argsName.get("delimiter").matches(";") && !argsName.get("delimiter").matches(",")) {
            throw new IllegalArgumentException("The delimiter must be ;");
        }
    }
}
