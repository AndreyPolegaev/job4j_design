package ru.job4j.generic.store;

/**
 * Контейнеры должны быть описаны интерфейсом
 */

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
