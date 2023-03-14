package com.app.project.controller;

import com.app.project.views.CLIView;

public class CLIController {
    private CLIView view;

    public CLIController(CLIView view) {
        this.view = view;
    }

    public void execute() {
        view.run();
    }
}
