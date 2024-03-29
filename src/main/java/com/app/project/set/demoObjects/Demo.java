package com.app.project.set.demoObjects;

import com.app.project.set.interfaces.ISetCollectionEntity;

public class Demo implements ISetCollectionEntity<Integer>,Cloneable {
    private static int counter = 0;
    private Integer Id;
    private String name;

    public Demo() {
        this.Id = counter + 1;
    }

    public Demo(String name) {
        this.Id = ++counter;
        this.name = name;
    }

    public Demo(String name, int Id) {
        this.Id = Id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    public String toString() {
        return "\n{Id:" + Id + "\tName:" + name+"}";
    }

}
