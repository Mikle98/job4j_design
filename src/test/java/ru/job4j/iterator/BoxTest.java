package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisSphereAndNotEmpty() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty();
    }

    @Test
    void getVerticesNotNegativeAndMoreThan4() {
        Box box = new Box(8, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isPositive()
                .isGreaterThan(4);
    }

    @Test
    void getVerticesNotZeroAndLessThan8() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isLessThan(8);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(4, 10);
        boolean bool = box.isExist();
        assertThat(bool).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(5, 10);
        boolean bool = box.isExist();
        assertThat(bool).isFalse();
    }

    @Test
    void getAreaPrecision200AndMoreThen5() {
        Box box = new Box(4, 10);
        double bool = box.getArea();
        assertThat(bool).isCloseTo(5.26d, withPrecision(200.006d))
                .isGreaterThan(5.25d);
    }

    @Test
    void getAreaPrecision2000AndLessThen5000() {
        Box box = new Box(0, 10);
        double bool = box.getArea();
        assertThat(bool).isCloseTo(5.26d, withPrecision(2000.006d))
                .isLessThan(5000.25d);
    }
}