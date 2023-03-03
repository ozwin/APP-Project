package com.app.project;

import com.app.project.datasource.DataStore;
import com.app.project.views.CLIView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        initialize with some mock data
        DataStore.initializeDataBase();
        CLIView view = new CLIView();
        view.run();
    }
}