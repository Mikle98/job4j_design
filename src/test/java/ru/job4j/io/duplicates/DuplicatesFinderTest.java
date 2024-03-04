package ru.job4j.io.duplicates;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class DuplicatesFinderTest {
    @Test
    public void checkDuplicatesFinder() throws IOException {
        DuplicateSearchAndValid duplSearch = new DuplicateSearchAndValid();
        Files.walkFileTree(Path.of("./JAVA"), duplSearch);
        String rsl = duplSearch.getDupl();
        String newLine = System.lineSeparator();
        assertThat(rsl).isEqualTo("[C:\\projects\\job4j_design\\.\\JAVA\\Text Document.txt - 4"
                + newLine + ", C:\\projects\\job4j_design\\.\\JAVA\\Новая папка\\Text Document.txt - 4"
                + newLine + ", C:\\projects\\job4j_design\\.\\JAVA\\Новая папка\\Новая папка\\Text Document.txt - 4"
                + newLine + "]");
    }
}