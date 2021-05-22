package ru.job4j.tree;

/**
 * Класс Node описывает узел дерева. Узел содержит хранимое значение и ссылки на дочерние узлы.
 * Метод add - Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
 * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
 * Если child есть, то метод должен вернуть false.
 * Давайте создадим класс реализующий интерфейс ru.job4j.tree.SimpleTree.
 * В классе ru.job4j.tree.Tree уже реализован метод findBy. Это класс использовать алгоритм обхода в ширину.
 * В этом задании мы не будем касаться устройства работы этого алгоритма.
 * Вам нужно воспользоваться результатом его работы для реализации метода add.
 */

import java.util.*;

class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(parent).isPresent()) {
            Node<E> node = findBy(parent).get();
            if (!node.children.contains(child)) {
                node.children.add(new Node<>(child));
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}