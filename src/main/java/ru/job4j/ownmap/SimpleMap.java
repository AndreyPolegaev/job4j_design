package ru.job4j.ownmap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int hashResult = hash(key.hashCode());
        int index = indexFor(hashResult);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] expandTable = new MapEntry[capacity];
        for (var temp : table) {
            if (temp != null) {
                int hashResult = hash(temp.key.hashCode());
                int index = indexFor(hashResult);
                expandTable[index] = temp;
            }
        }
        table = expandTable;
    }

    @Override
    public V get(K key) {
        int hashResult = hash(key.hashCode());
        int index = indexFor(hashResult);
        if (table[index] == null) {
            return null;
        }
        if (table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int hashResult = hash(key.hashCode());
        int index = indexFor(hashResult);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {

        Iterator<K> it = new Iterator<K>() {
            int countIterator = 0;
            MapEntry<K, V> mapNext = new MapEntry<>(null, null);
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = countIterator; i < table.length; i++) {
                    var element = table[i];
                    if (element != null) {
                        mapNext = element;
                        countIterator = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                countIterator++;
                return mapNext.key;
            }
        };
        return it;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}