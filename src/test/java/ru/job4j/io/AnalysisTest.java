package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    public void whenTwoRange(@TempDir Path templDir) throws IOException {
        File server = templDir.resolve("server.log").toFile();
        try (PrintWriter writer = new PrintWriter(server)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("300 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        File target = templDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(server.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            reader.lines().forEach(line -> rsl.append(line).append("\n"));
        }
        assertThat("""
                10:57:01;10:59:01;
                11:01:02;11:02:02;
                """).hasToString(rsl.toString());
    }

    @Test
    void whenOneRange(@TempDir Path templDir) throws IOException {
        File server = templDir.resolve("server.log").toFile();
        try (PrintWriter writer = new PrintWriter(server)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("300 11:02:02");
        }
        File target = templDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(server.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            reader.lines().forEach(line -> rsl.append(line).append("\n"));
        }
        assertThat("""
                10:57:01;11:02:02;
                """).hasToString(rsl.toString());
    }
}