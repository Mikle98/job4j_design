package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleArraySet<T> implements SimpleSet<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (equals(t, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    public boolean equals(T a, T b) {
        boolean rsl;
        if (a == null && b == null) {
            rsl = true;
        } else if (a == null) {
            rsl = false;
        } else {
            rsl = a.equals(b);
        }
        return rsl;
    }
}
