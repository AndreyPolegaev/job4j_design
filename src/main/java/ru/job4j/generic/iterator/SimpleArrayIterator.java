package ru.job4j.generic.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {

    private final T[] data;
    int iteratorCount = 0;
    int count;

    public SimpleArrayIterator(T[] data, int count) {
        this.data = data;
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        return iteratorCount < count;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[iteratorCount++];
    }
}
