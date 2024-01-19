package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {
    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<E> tmplHead = head;
            while (tmplHead.next != null) {
                tmplHead = tmplHead.next;
            }
            tmplHead.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }


    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tmplHead = head;
        while (index != 0) {
            tmplHead = tmplHead.next;
            index--;
        }
        return tmplHead.item;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = modCount;
            Node<E> tmplHead = head;
            E rsl;
            @Override
            public boolean hasNext() {
                if (modCount != index) {
                    throw new ConcurrentModificationException();
                }
                return tmplHead != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                rsl = tmplHead.item;
                tmplHead = tmplHead.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
