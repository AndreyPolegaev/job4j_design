package ru.job4j.iterator;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BackwardArrayItTest {

    @Test
    public void checkBack() {
        int[] data = new int[] {1, 2, 3};
        BackwardArrayIt backwardArrayIt = new BackwardArrayIt(data);
        assertTrue(backwardArrayIt.hasNext());
        assertTrue(backwardArrayIt.hasNext());
    }

    @Test
    public void whenTakeElementsFromEnd() {
        int[] data = new int[] {1, 2, 3};
        BackwardArrayIt backwardArrayIt = new BackwardArrayIt(data);
        assertThat(backwardArrayIt.next(), is(3));
        assertThat(backwardArrayIt.next(), is(2));
        assertThat(backwardArrayIt.next(), is(1));
        assertFalse(backwardArrayIt.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}