package com.app.project.interfaces;

import com.app.project.entity.Property;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

public interface IPropertyView {
    void displayProperty(IProperty property);

    void displayProperties(ArrayList<IProperty> properties);

    void add();
}
