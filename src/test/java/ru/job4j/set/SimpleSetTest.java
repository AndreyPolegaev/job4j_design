package ru.job4j.set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenIterator() {
        Set<String> set = new SimpleSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("One"));
        assertThat(it.next(), is("Two"));
        assertThat(it.next(), is("Three"));
        assertThat(it.next(), is("Four"));
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenException() {
        Set<String> set = new SimpleSet<>();
        set.add("One");
        set.add("Two");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("One"));
        set.add("Five");
        it.next();
    }
}