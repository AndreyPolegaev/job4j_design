package ru.job4j.generic.simplequeue;

import ru.job4j.generic.stack.SimpleStack;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            exChange();
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }

    private void exChange() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
    }
}
