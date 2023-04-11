package com.app.project.set;

import com.app.project.set.interfaces.ISet;
import com.app.project.set.interfaces.ISetCollectionEntity;

import java.util.HashMap;
import java.util.List;

public class Set<E extends ISetCollectionEntity<K>,K> implements ISet<E,K>,Cloneable {

    private HashMap<K, E> data;
    private int size;

    public Set() {
        size = 0;
        data = new HashMap<K, E>();
    }

    @Override
    public boolean add(E entity) {
        if (data.containsKey(entity.getId()))
            return false;
        data.put(entity.getId(), entity);
        size++;
        return true;
    }

    @Override
    public E remove(K ID) {
        size--;
        return data.remove(ID);
    }

    @Override
    public boolean peek(K ID) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Set<E,K> set = ( Set<E,K>) obj;
        if(set.size()!=this.size()) return false;
            //check for all the values
        List<E> elements=set.getAll();
        for (E element:elements){
            if(!this.peek(element.getId()))
                return false;
        }
        return true;
    }
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Set<E,K> temp = ( Set<E,K>) obj;
        Set<E,K> set=new Set<E,K>();
        for(E element:temp.getAll()) set.add(element);
        return set;
    }

}
