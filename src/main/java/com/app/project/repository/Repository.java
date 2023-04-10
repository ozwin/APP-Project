package com.app.project.repository;

import com.app.project.interfaces.IEntity;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Repository<T extends IEntity, K> implements IRepository<T, K> {
    protected ArrayList<T> datastore;

    public Repository() {
        datastore = new ArrayList<T>();
    }

    /**
     * Find the object by the ID using the getId interface .
     *
     * @param key ID of the object
     * @return object of type T
     */
    @Override
    public T findByKey(K key) {
        return datastore.stream().filter(r -> r.getID().equals(key)).findFirst().orElse(null);
    }

    /**
     * Get all the objects from database of the instantiated type
     *
     * @return
     */
    public ArrayList<T> getAll() {
        return datastore;
    }

    /**
     * insert a entity into the database.
     *
     * @param entity entity of the instantiated type
     * @return
     */
    public void insert(T entity) {
        this.datastore.add(entity);
    }

    public void delete(T entity) {
        this.datastore.remove(entity);
    }

    public void upsert(T entity) {
        int index = this.datastore.indexOf(entity);
        if (index >= 0)
            this.datastore.set(index, entity);
        else
            this.datastore.add(entity);
    }

    public ArrayList<T> findMany(List<K> IDs) {
        return this.datastore.stream().filter(t -> IDs.contains(t.getID())).collect(Collectors.toCollection(ArrayList::new));
    }
}
