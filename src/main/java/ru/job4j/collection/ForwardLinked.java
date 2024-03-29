package ru.job4j.collection;

import java.util.*;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public void add(T value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<T> tmplHead = head;
            while (tmplHead.next != null) {
                tmplHead = tmplHead.next;
            }
            tmplHead.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> tmplHead = head;
        while (index != 0) {
            tmplHead = tmplHead.next;
            index--;
        }
        return tmplHead.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.item;
        Node<T> tmplHead = head.next;
        head.item = null;
        head.next = null;
        head = tmplHead;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = modCount;
            Node<T> tmplHead = head;
            T rsl;
            @Override
            public boolean hasNext() {
                if (modCount != index) {
                    throw new ConcurrentModificationException();
                }
                return tmplHead != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                rsl = tmplHead.item;
                tmplHead = tmplHead.next;
                return rsl;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
