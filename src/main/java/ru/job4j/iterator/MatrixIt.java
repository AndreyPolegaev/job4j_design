package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int[] rsl;
    private int elem = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        rsl = new int[Stream.of(data).mapToInt(x -> x.length).sum()];
        rsl = Stream.of(data).flatMapToInt(Arrays::stream).toArray();
    }

    @Override
    public boolean hasNext() {
        return elem < rsl.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return rsl[elem++];
    }
}