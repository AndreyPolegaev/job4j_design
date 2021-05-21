package ru.job4j.ownmap;

import org.junit.Test;

import java.time.Period;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutTrueThenFalseIfCellIsNotFree() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("Petr", 25));
        assertFalse(simpleMap.put("Petr", 25));
    }

    @Test
    public void whenPutElementsForIncreaseHashTable() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("Petr", 25));
        assertTrue(simpleMap.put("Sasha", 54));
        assertTrue(simpleMap.put("Michail", 13));
        assertTrue(simpleMap.put("Vladimir", 35));
        assertTrue(simpleMap.put("Olga", 43));
        assertTrue(simpleMap.put("Andrey", 23));
        assertTrue(simpleMap.put("Nikolai", 76));
        assertFalse(simpleMap.put("Karina", 24));
        assertFalse(simpleMap.put("Margarita", 54));
        assertFalse(simpleMap.put("Olga Buzova", 33));
    }

    @Test
    public void whenGetTrueAndThenNullValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        simpleMap.put("Olga", 15);
        simpleMap.put("Andrey", 30);
        assertThat(simpleMap.get("Andrey"), is(30));
        assertThat(simpleMap.get("Olga"), is(15));
        assertThat(simpleMap.get("Petr"), is(25));
        assertNull(simpleMap.get("Vladimir"));
    }

    @Test
    public void whenCheckGetWhileHashCodeInTheNewTableWasSave() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("Petr", 25));
        assertTrue(simpleMap.put("Sasha", 54));
        assertTrue(simpleMap.put("Michail", 13));
        assertTrue(simpleMap.put("Vladimir", 35));
        assertTrue(simpleMap.put("Olga", 43));
        assertTrue(simpleMap.put("Andrey", 23));
        assertTrue(simpleMap.put("Nikolai", 76));
        assertFalse(simpleMap.put("Karina", 24));
        assertFalse(simpleMap.put("Margarita", 54));
        assertFalse(simpleMap.put("Olga Buzova", 33));
        assertThat(simpleMap.get("Nikolai"), is(76));
        assertThat(simpleMap.get("Michail"), is(13));
        assertThat(simpleMap.get("Vladimir"), is(35));
    }

    @Test
    public void whenPutThenRemove() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        simpleMap.put("Olga", 30);
        simpleMap.remove("Petr");
        Iterator<String> it = simpleMap.iterator();
        assertThat(it.next(), is("Olga"));
    }

    @Test
    public void whenPutThenRemoveTrueAndFalse() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        simpleMap.put("Olga", 30);
        assertTrue(simpleMap.remove("Petr"));
        assertTrue(simpleMap.remove("Olga"));
        assertFalse(simpleMap.remove("Olga"));
        assertFalse(simpleMap.remove("Olga"));
    }

    @Test
    public void whenPutThenRemoveThenPut() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        simpleMap.put("Olga", 30);
        assertTrue(simpleMap.remove("Petr"));
        simpleMap.put("Andrey", 30);
        assertTrue(simpleMap.remove("Andrey"));
        assertFalse(simpleMap.remove("Andrey"));
    }

    @Test
    public void whenSingleElementIteratorTest() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        Iterator<String> it = simpleMap.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("Petr"));
    }

    @Test
    public void whenSeveralElementsPresents() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        simpleMap.put("Olga", 30);
        Iterator<String> it = simpleMap.iterator();
        assertNotNull(it.next());
        assertNotNull(it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextElementIsAbsent() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        Iterator<String> it = simpleMap.iterator();
        assertNotNull(it.next());
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorException() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Petr", 25);
        Iterator<String> it = simpleMap.iterator();
        assertThat(it.next(), is("Petr"));
        simpleMap.put("Michail", 20);
        it.next();
    }
}