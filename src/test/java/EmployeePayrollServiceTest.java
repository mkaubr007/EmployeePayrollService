import com.biz.employee.payroll.model.EmployeePayRoll;
import com.biz.employee.payroll.service.EmployeePayrollService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollServiceTest {
    @Test
    public void given3EmployeeEntries_WhenWrittenToFileShouldMatchEmployeeEntries(){
        List<EmployeePayRoll>employeePayRollList=new ArrayList<>();
        employeePayRollList.add(new EmployeePayRoll(1,"manish",34857));
        employeePayRollList.add(new EmployeePayRoll(2,"roshan",46436));
        employeePayRollList.add(new EmployeePayRoll(3,"rohit",355));
        EmployeePayrollService service=new EmployeePayrollService(employeePayRollList);
        service.writeFileData();

    }

}