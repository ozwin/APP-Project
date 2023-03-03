package com.app.project.controller;

import com.app.project.entity.Tenant;
import com.app.project.service.TenantServices;
import com.app.project.views.TenantView;

import java.util.ArrayList;

public class TenantController {
    private TenantServices tenantServices;
    private TenantView view;
    public TenantController(TenantView view){
        this.tenantServices=new TenantServices();//can be injected using IOC framework
        this.view=view;
        this.view.setController(this);
    }
    public void addTenant(Tenant tenant){
        this.tenantServices.add(tenant);
    }
    public void addTenantView(){
        view.add();
    }
    public void displayAllTenants(){
        ArrayList<Tenant> tenants= this.tenantServices.getAll();
        view.displayAll(tenants);
    }

}
