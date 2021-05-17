package ru.job4j.generic.stack;
/**
 * push добавляем в конец очереди
 */

import ru.job4j.generic.deleteheadlinkedlist.ForwardLinked;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}