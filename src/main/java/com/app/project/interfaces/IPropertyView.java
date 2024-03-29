package com.app.project.interfaces;

import java.util.ArrayList;

public interface IPropertyView {
    void displayProperty(IProperty property);

    void displayProperties(ArrayList<IProperty> properties);

    void add();
}
