package ru.job4j.generic.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private int count = 0;
    private T[] data;

    public SimpleArray(int size) {
        this.data = ((T[]) new Object[size]);
    }

    public void add(T model) {
        data[count++] = model;
    }

    public void set(int index, T model) {
        int setIndex = Objects.checkIndex(index, count);
        data[setIndex] = model;
    }

    public void remove(int index) {
        int removeIndex = Objects.checkIndex(index, count);
        System.arraycopy(data, removeIndex + 1, data, removeIndex, data.length - index - 1);
        data[data.length - 1] = null;
        count--;
    }

    public T get(int index) {
        int getIndex = Objects.checkIndex(index, count);
        return data[getIndex];
    }

    @Override
    public Iterator iterator() {
        return new SimpleArrayIterator(data);
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "data=" + Arrays.toString(data)
                + '}';
    }
}
