package ru.job4j.generic.iterator;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenCatchError() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(0);
        simpleArray.get(1);
    }

    @Test
    public void whenSet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.set(1, 100);
        assertThat(simpleArray.get(1), is(100));
    }

    @Test
    public void whenRemove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.remove(0);
        assertThat(simpleArray.get(0), is(2));
    }

    @Test
    public void whenRemoveAndAddAgain() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(5);
        simpleArray.add(10);
        simpleArray.remove(0);
        simpleArray.add(15);
        assertThat(simpleArray.get(0), is(10));
        assertThat(simpleArray.get(1), is(15));
    }

    @Test
    public void whenAddElementsThenGet() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    @Test
    public void whenApplyIterator() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        simpleArray.add(1);
        simpleArray.add(2);
        Iterator<Integer> it = simpleArray.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenApplyIteratorNext() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(-5);
        simpleArray.add(0);
        simpleArray.add(1);
        simpleArray.add(2);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(-5));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertFalse(it.hasNext());
    }
}