package com.app.project.set;

import com.app.project.set.interfaces.ISet;
import com.app.project.set.interfaces.ISetCollectionEntity;

import java.util.HashMap;
import java.util.List;

public class Set<E extends ISetCollectionEntity> implements ISet<E> {

    private HashMap<Integer,E> data;
    private int size;

    public Set(){
        size=0;
        data=new HashMap<Integer,E>();
    }
    @Override
    public boolean add(E entity) {
        if(data.containsKey(entity.getId()))
            return false;
        data.put(entity.getId(),entity);
        size++;
        return true;
    }
    @Override
    public E remove(int ID) {
        size--;
        return data.remove(ID);
    }
    @Override
    public boolean peek(int ID) {
        return data.containsKey(ID);
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public List<E> getAll() {
        return data.values().stream().toList();
    }
}
