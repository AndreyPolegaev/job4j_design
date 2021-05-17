package ru.job4j.generic.linkedlist;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        List<String> list = new SimpleLinkedList<>();
        list.add("First string");
        list.add("Second string");
        Iterator<String> it = list.iterator();
        it.next();
        list.add("Third string that will invoke exception");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        List<String> list = new SimpleLinkedList<>();
        list.add("First string");
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
    }

    @Test
    public void whenGetFirstAndLastElement() {
        List<Character> list = new SimpleLinkedList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        assertThat(list.get(0), is('a'));
        assertThat(list.get(3), is('d'));
    }

    @Test
    public void whenAddAndGetNull() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        list.add(1);
        assertNull(list.get(0));
        assertThat(list.get(1), Is.is(1));
    }

    @Test
    public void whenApplyToStringTypeOfString() {
        List<String> list = new SimpleLinkedList<>();
        list.add("Мама");
        list.add("Мыла");
        list.add("Раму");
        String rsl = list.toString();
        assertEquals(rsl, "[Мама Мыла Раму]");
    }

    @Test
    public void whenApplyToStringTypeOfFigures() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        String rsl = list.toString();
        assertEquals(rsl, "[1 2 3]");
    }
}