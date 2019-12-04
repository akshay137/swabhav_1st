package com.techlabs.employee;

public interface IEmployeeComposite {
    void display(int level);
    void add(IEmployeeComposite emp);
}
