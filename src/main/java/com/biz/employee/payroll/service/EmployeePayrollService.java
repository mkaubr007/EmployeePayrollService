package com.biz.employee.payroll.service;
import com.biz.employee.payroll.model.EmployeePayRoll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    static  Scanner scan=new Scanner(System.in);
    static List<EmployeePayRoll> list=new ArrayList<>();
    public static void main(String[] args) {
        EmployeePayRoll employeePayRoll=new EmployeePayRoll();
        System.out.println("Enter your Id");
        int id=scan.nextInt();
        employeePayRoll.setId(id);
        System.out.println("Enter your name");
        String name=scan.next();
        employeePayRoll.setName(name);
        System.out.println("Enter your salary");
        double salary=scan.nextDouble();
        employeePayRoll.setSalary(salary);
        list.add(employeePayRoll);
        print();
    }
    public static void print() {
        Iterator<EmployeePayRoll> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
