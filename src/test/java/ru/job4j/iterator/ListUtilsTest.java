package ru.job4j.iterator;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;
    Predicate<Integer> filter;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
        filter = (p) -> p < 3;
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(2, 1, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input).hasSize(2).containsSequence(0, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 1, 3, 5, 1));
        ListUtils.removeAll(list, input);
        assertThat(list).hasSize(2).containsSequence(5, 5);
    }
}