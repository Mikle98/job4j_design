package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class RoleStore<T extends Base> implements Store<T> {
    private final MemStore<T> memStore = new MemStore<>();

    @Override
    public void add(T model) {
        memStore.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        return memStore.replace(id, model);
    }

    @Override
    public void delete(String id) {
        memStore.delete(id);
    }

    @Override
    public T findById(String id) {
        return memStore.findById(id);
    }
}
