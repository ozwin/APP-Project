package com.app.project.util;

import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    public Scanner scanner;

    private ScannerSingleton() {
        scanner = new Scanner(System.in);
    }

    public static synchronized ScannerSingleton getInstance() {
        if (instance == null)
            instance = new ScannerSingleton();
        return instance;
    }

}
