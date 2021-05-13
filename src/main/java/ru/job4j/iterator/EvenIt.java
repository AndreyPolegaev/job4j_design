package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenIt implements Iterator<Integer> {

    private int[] numbers;
    int count = 0;
    int nextElement;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        for (int i = count; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                nextElement = numbers[i];
                count = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        count++;
        return nextElement;
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
      Arrays.stream(numbers).boxed().forEach(action);
    }
}
