package com.biz.employee.payroll.service;
import com.biz.employee.payroll.model.EmployeePayRoll;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeePayrollService {
    ArrayList<EmployeePayRoll> employeePayRolls;
    Scanner scan = new Scanner(System.in);

    public EmployeePayrollService(ArrayList<EmployeePayRoll> employeePayRolls) {
        this.employeePayRolls = employeePayRolls;
    }

    private void readData(){
        EmployeePayRoll employeePayRoll=new EmployeePayRoll();
        System.out.println("Enter your Id");
        employeePayRoll.setId(scan.nextInt());
        System.out.println("Enter your name");
        employeePayRoll.setName(scan.next());
        System.out.println("Enter your salary");
        employeePayRoll.setSalary(scan.nextDouble());
        employeePayRolls.add(employeePayRoll);
    }
    private void writeData(){
        System.out.println(employeePayRolls);
    }

}
