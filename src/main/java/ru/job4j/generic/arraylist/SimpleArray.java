package ru.job4j.generic.arraylist;
/**
 * size - Кол-во элементов, + для размещения элементов
 * modCount = 0; счетчик модификаций массива
 */

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private T[] container;

    @SuppressWarnings("unchecked")
    public SimpleArray() {
        this.container = (T[]) new Object[10];
    }

    public T get(int index) {
        int getIndex = Objects.checkIndex(index, size);
        return container[getIndex];
    }

    public void add(T model) {
        if (size < container.length) {
            container[size++] = model;
            modCount++;
        } else {
            increaseArray(model);
        }
    }
    private void increaseArray(T model) {
        int newLength = ((size * 3) / 2) + 1;
        container = Arrays.copyOf(container, newLength);
        container[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            int iteratorCount = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorCount < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[iteratorCount++];
            }
        };
        return it;
    }

    @Override
    public String toString() {
        return Arrays.toString((Arrays.copyOf(container, size)));
    }
}
