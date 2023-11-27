package ru.job4j.iterator;

import java.util.*;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data = new ArrayList<>();
    private int index;

    public CyclicIterator(List<T> data) {
        this.data.addAll(data);
    }

    @Override
    public boolean hasNext() {
        if (index >= data.size()) {
            index = 0;
        }
        return data.size() != 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(index++);
    }
}
