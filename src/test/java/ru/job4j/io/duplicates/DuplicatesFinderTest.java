package ru.job4j.io.duplicates;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DuplicatesFinderTest {
    @Test
    public void checkDuplicatesFinder() throws IOException {
        DuplicateSearchAndValid duplSearch = new DuplicateSearchAndValid();
        Files.walkFileTree(Path.of("./JAVA"), duplSearch);
        String rsl = duplSearch.getDupl();
        List<String> ex = new ArrayList<>();
        Path path = Path.of("./JAVA/Text Document.txt");
        ex.add(String.format("%s - %d%n", path.toAbsolutePath(), path.toFile().length()));
        Path path2 = Path.of("./JAVA/Новая папка/Text Document.txt");
        ex.add(String.format("%s - %d%n", path2.toAbsolutePath(), path2.toFile().length()));
        Path path3 = Path.of("./JAVA/Новая папка/Новая папка/Text Document.txt");
        ex.add(String.format("%s - %d%n", path3.toAbsolutePath(), path3.toFile().length()));
        assertThat(rsl).isEqualTo(ex.toString());
    }
}