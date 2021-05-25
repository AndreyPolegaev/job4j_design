package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        Optional<Node<E>> checkNodeSize = findByPredicate(x -> x.children.size() > 2);
        return checkNodeSize.isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> nodeSearch = findBy(parent);
        if (nodeSearch.isPresent()) {
            Node<E> nodeParent = nodeSearch.get();
            Optional<Node<E>> searchChild = findByPredicate(x -> x.value.equals(child));
            if (searchChild.isEmpty()) {
                nodeParent.children.add(new Node<>(child));
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
       return findByPredicate(x -> x.value.equals(value));
    }
}