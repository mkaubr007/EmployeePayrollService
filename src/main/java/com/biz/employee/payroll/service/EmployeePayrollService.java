package com.biz.employee.payroll.service;
import com.biz.employee.payroll.model.EmployeePayRoll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService extends RuntimeException{
    List<EmployeePayRoll> employeePayRolls;
    Scanner scan = new Scanner(System.in);

    public EmployeePayrollService(List<EmployeePayRoll> employeePayRollList) {
        this.employeePayRolls=employeePayRollList;
    }

    public void writeFileData(){
        String payrollFileName="D:\\EmployeePayrollService\\src\\main\\resources\\temp.txt";

        StringBuffer empDataBuffer=new StringBuffer();
        employeePayRolls.forEach(data->{
            String dataString=data.toString().concat("\n");
            empDataBuffer.append(dataString);
        });
        try{
            Files.write(Paths.get(payrollFileName),empDataBuffer.toString().getBytes());
        }catch (IOException e){
            System.out.println(e);
        }
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

}
