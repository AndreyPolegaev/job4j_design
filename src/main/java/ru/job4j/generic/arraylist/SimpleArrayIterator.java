package ru.job4j.generic.arraylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {

    private final T[] container;
    private final int expectedModCount;
    private final int size;
    private int iteratorCount = 0;

    public SimpleArrayIterator(T[] container, int modCount, int size) {
        this.container = container;
        this.expectedModCount = modCount;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
//        if (expectedModCount != modCount) {
//            throw new ConcurrentModificationException();
//        }
        return iteratorCount < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return container[iteratorCount++];
    }
}
