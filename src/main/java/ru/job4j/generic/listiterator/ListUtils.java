package ru.job4j.generic.listiterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.nextIndex() == index) {
                list.add(index + 1, value);
                break;
            }
            it.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        Predicate<T> predicate = filter;
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        Predicate<T> predicate = filter;
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> it = elements.listIterator();
        while (it.hasNext()) {
            T el = it.next();
            list.remove(el);
        }
    }
}
