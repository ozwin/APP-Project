package com.app.project.set.interfaces;

import java.util.List;

public interface ISet<E> {
    boolean add(E entity);

    E remove(int ID);

    boolean peek(int ID);

    int size();

    List<E> getAll();
}
