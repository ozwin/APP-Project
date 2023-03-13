package com.app.project.views;

import com.app.project.controller.LeaseController;
import com.app.project.entity.Lease;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LeaseView {
    private LeaseController controller;

    private Scanner scanner;
    public LeaseView(Scanner scanner){
        this.scanner = scanner;
    }

    public void add(UUID property, List<UUID> tenants){
        System.out.println("Enter the duration of the lease");
        int duration = scanner.nextInt();
        Lease lease = new Lease(tenants, property);
        lease.setLeaseDuration(duration);
        controller.addLease(lease);
    }

    public void setController(LeaseController controller) {
        this.controller = controller;
    }
}
