package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenFileWithSpaceAndCommnet() {
        String path = "./data/pair_with_comment_and_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Test");
        assertThat(config.value("")).isEqualTo(null);
    }

    @Test
    void whenFileBadFormat() {
        String path = "./data/pair_bad_format.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNonStandart() {
        String path = "./data/pair_non_standart.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Name=");
        assertThat(config.value("name2")).isEqualTo("Name2=Name1");
    }
}