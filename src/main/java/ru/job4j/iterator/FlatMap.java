package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.hasNext();
    }

    @Override
    public T next() {
        if (cursor.hasNext()) {
            return cursor.next();
        } else {
            cursor = data.next();
        }
        return next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
        System.out.println(flat.next());
    }
}

