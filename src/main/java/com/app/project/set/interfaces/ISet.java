package com.app.project.set.interfaces;

import java.util.List;

public interface ISet<E,K> {
    boolean add(E entity);

    E remove(K ID);

    boolean peek(K ID);

    int size();

    List<E> getAll();
}
