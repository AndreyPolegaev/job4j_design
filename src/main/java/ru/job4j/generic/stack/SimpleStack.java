package ru.job4j.generic.stack;

import ru.job4j.generic.deleteheadlinkedlist.ForwardLinked;

/**
 * push добавляем в начало очереди, удаяем из начала
 */

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return linked.isEmpty();
    }
}