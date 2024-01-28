package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int index = key == null ? 0 : getIndexKey(key.hashCode());
        if (index != -1) {
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                rsl = true;
                count++;
                modCount++;
            }
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int index;
        K key;
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        Iterator<K> oldKey = iterator();
        while (oldKey.hasNext()) {
            key = oldKey.next();
            index = key == null ? 0 : getIndexKey(key.hashCode());
            newTable[index] = new MapEntry<>(key, get(key));
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        return searchKey(key) != -1 ? table[searchKey(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (searchKey(key) != -1) {
            table[searchKey(key)] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int i;
            final int iterateMod = modCount;

            @Override
            public boolean hasNext() {
                if (iterateMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table.length > i && table[i] == null) {
                    i++;
                }
                return table.length > i && table[i] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int getIndexKey(int keyHashCode) {
        return indexFor(hash(keyHashCode));
    }

    private int searchKey(K key) {
        int rsl = -1;
        int index = key == null ? 0 : getIndexKey(key.hashCode()) % table.length;
        if (table[index] != null) {
            if (key == null || (table[index].key != null && table[index].key.hashCode() == key.hashCode())) {
                if ((key == null && table[index].key == null) || table[index].key.equals(key)) {
                    rsl = index;
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        NonCollisionMap<Iterator, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
    }
}
