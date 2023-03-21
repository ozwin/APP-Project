package com.app.project.repository;

import com.app.project.interfaces.IEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Repository<T extends IEntity,K> {
    private ArrayList<T> entities;
    public T findByKey(K key){
        return entities.stream().filter(r -> r.getID().equals(key)).findFirst().orElse(null);
    }
    public List<T> getAll(){
        return entities;
    }
    public void insert(T entity){
        this.entities.add(entity);
    }
    public void delete(T entity){
        this.entities.remove(entity);
    }
}
