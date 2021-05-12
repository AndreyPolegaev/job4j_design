package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new EvenIt(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenIt(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenIt(new int[] {2, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }

    @Test
    public void whenRemoveFirstElement() {
        it = new EvenIt(new int[] {1, 1, 2, 3, 3, 4, 6, 8, 10});
        it.next();
        it.remove();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
    }

    @Test
    public void whenRemoveAllElements() {
        it = new EvenIt(new int[] {2, 4, 6, 8});
        it.next();
        it.next();
        it.next();
        it.next();
        it.remove();
        it.remove();
        it.remove();
        it.remove();
        assertFalse(it.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveWithoutNext() {
        it = new EvenIt(new int[] {2, 4, 6, 8});
        it.remove();
    }

    @Test
    public void whenChangesArrayForEach() {
        it = new EvenIt(new int[] {1, 2, 3, 4});
        it.forEachRemaining(x -> System.out.println(Math.pow(2, x)));
    }
}