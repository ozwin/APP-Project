package com.app.project.util;

import java.util.Scanner;

public class ScannerSingleton {
    private ScannerSingleton(){
        scanner=new Scanner(System.in);
    }
    private static ScannerSingleton instance;
    public Scanner scanner;
    public static synchronized ScannerSingleton getInstance(){
        if(instance==null)
            instance=new ScannerSingleton();
        return instance;
    }

}
