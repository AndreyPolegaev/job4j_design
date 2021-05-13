package ru.job4j.generic.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {

    private T[] data;
    int count = 0;

    public SimpleArrayIterator(T[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (count < data.length) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[count++];
    }
}
