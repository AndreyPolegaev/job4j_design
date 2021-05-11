package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EvenIt implements Iterator<Integer> {

    private int[] numbers;
    int count = 0;

    public EvenIt(final int[] numbers) {
        this.numbers = Arrays.stream(numbers)
                .filter(x -> x % 2 == 0)
                .toArray();
    }

    @Override
    public boolean hasNext() {
        return count < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[count++];
    }

    @Override
    public void remove() {
        if (count == 0) {
            throw new IllegalStateException();
        }
        System.arraycopy(numbers, count, numbers, count - 1, numbers.length - count);
        numbers = Arrays.copyOf(numbers, numbers.length - 1);
        count--;
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
      Arrays.stream(numbers).boxed().forEach(action);
    }
}
