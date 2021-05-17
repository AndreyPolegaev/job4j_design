package ru.job4j.generic.linkedlist;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleLinkedList<E> implements List<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> firstNode;
    private Node<E> lastNode;

    @Override
    public void add(E value) {
        final Node<E> l = lastNode;
        final Node<E> newNode = new Node<>(l, value, null);
        lastNode = newNode;
        if (l == null) {
            firstNode = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> invocation = firstNode;
        int checkedIndex = Objects.checkIndex(index, size);
        if (checkedIndex == 0) {
            return this.firstNode.item;
        } else {
            int i = 0;
            while (i != checkedIndex) {
                Node<E> stepNode = invocation.next;
                invocation = stepNode;
                i++;
            }
        }
        return invocation.item;
    }

    @Override
    public String toString() {
        int count = 0;
        StringJoiner stringJoiner = new StringJoiner(" ");
        while (count < size) {
            stringJoiner.add(String.valueOf(get(count)));
            count++;
        }
        String rsl = stringJoiner.toString()
                .chars()
                .mapToObj(s -> (char) s)
                .map(String::valueOf)
                .collect(Collectors.joining("", "[", "]"));
        return rsl;
    }

    private static class Node<E> {

        private final E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<>() {
            final int expectedModCount = modCount;
            int count = 0;
            Node<E> nextNode = firstNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public E next() {
                E item = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                item = nextNode.item;
                nextNode = nextNode.next;
                count++;
                return item;
            }
        };
        return it;
    }
}
