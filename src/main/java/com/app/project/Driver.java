package com.app.project;

import com.app.project.set.Set;
import com.app.project.set.demoObjects.Demo;

public class Driver {
    public static void main(String[] args) {
        try {
            Set<Demo, Integer> demoSet = new Set<Demo, Integer>();
            Demo demo1 = new Demo("Demo1");
            Demo demo2 = new Demo("Demo2");
            Demo demo3 = new Demo("Demo3");
            Demo demo4 = new Demo("Demo4");
            Demo demo5 = new Demo("Demo5");
            Demo demo1Duplicate = new Demo("Demo1", 1);
            Demo demo2Duplicate = new Demo("Demo2", 2);
            demoSet.add(demo1);
            demoSet.add(demo2);
            demoSet.add(demo3);
            System.out.println("After 3 insertion size is " + demoSet.size());
            if (demoSet.add(demo1Duplicate))
                System.out.println("Insertion Successful for duplicates");
            else
                System.out.println("Item already exists in the set");
            if (demoSet.add(demo2Duplicate))
                System.out.println("Insertion Successful for duplicates");
            else
                System.out.println("Item already exists in the set");
            demoSet.add(demo4);
            demoSet.add(demo5);
            System.out.println("After 5 insertion size is " + demoSet.size());
            System.out.println("Removing item with Id 1:" + demoSet.remove(demo1.getId()));
            System.out.println("Peeking item with Id 1: " + demoSet.peek(demo1.getId()));
            System.out.println("Inserting item with Id 1");
            demoSet.add(demo1Duplicate);
            System.out.println("Peeking item with Id 1: " + demoSet.peek(demo1.getId()));
            System.out.println("Print all elements\n" + demoSet.getAll());
            System.out.println("Let's clone the Set1 to Set2");
            Set<Demo, Integer> demoSet2 = new Set<Demo, Integer>();
            demoSet2 =(Set<Demo, Integer>) demoSet.clone();
            System.out.println("Print all elements in Set1\n" + demoSet.getAll());
            System.out.println("Print all elements in Set2\n" + demoSet2.getAll());
            System.out.println("Are set1 and set2 equal?" + (demoSet.equals(demoSet2)?"Yes":"No"));
            Demo demoNew = new Demo("Demo-new");
            System.out.println("Let's add an element to set 1" + demoNew);
            demoSet.add(demoNew);
            System.out.println("Are set1 and set2 equal?" + (demoSet.equals(demoSet2)?"Yes":"No"));
            System.out.println("Print all elements in Set1\n" + demoSet.getAll());
            System.out.println("Print all elements in Set2\n" + demoSet2.getAll());
        }catch (Exception ex){
            System.out.println("OOPS!");
        }
    }

}
