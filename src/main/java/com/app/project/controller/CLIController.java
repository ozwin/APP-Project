package com.app.project.controller;

import com.app.project.views.CLIView;

/**
 * This class consists implementation of Controller for Command Line Interface.
 */
public class CLIController {
    private CLIView view;

    public CLIController(CLIView view) {
        this.view = view;
    }

    public void execute() {
        view.run();
    }
}
