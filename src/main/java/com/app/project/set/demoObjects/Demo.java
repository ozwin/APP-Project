package com.app.project.set.demoObjects;

import com.app.project.set.interfaces.ISetCollectionEntity;

public class Demo implements ISetCollectionEntity {
    private int Id;
    private String name;
    private static int counter=0;
    public Demo(){
        this.Id=counter+1;
    }
    public Demo(String name){
        this.Id=++counter;
        this.name=name;
    }
    public Demo(String name,int Id){
        this.Id=Id;
        this.name=name;
    }
    @Override
    public int getId() {
        return this.Id;
    }
    public String toString(){
        return "Id:"+Id+"\tName:"+name;
    }
}