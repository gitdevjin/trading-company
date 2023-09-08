package button;

import query.EmployeeQuery;
import table.Employee;
import utility.Tool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeButton {
    private EmployeeQuery EQuery;
    private int page;

    public EmployeeButton() {
        this.EQuery = new EmployeeQuery();
        this.page = 1;
    }

    public void printEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> list = EQuery.getEmployees(page);
        int count = EQuery.getCount();
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.printf("<Employees of Mega Trading Company> : Total %d \n", count);
        System.out.println("------------------------------------------------");
        System.out.printf("%4s  %-23s   %-23s  \n", "ID", "NAME", "JOB_TITLE");
        for (Employee e : list) {
            System.out.printf(
                    "%4d. %-23s   %-23s  \n",
                    e.getEmployeeID(),
                    e.getFirstName() + " " + e.getLastName(),
                    e.getJobTitle());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("                   %d/%d pages                 \n", page, count / 10 + 1);
        System.out.println("1. Refresh / 2. Previous / 3. Next / 4. Details ");
        System.out.println("0. GO BACK");
    }

    public void movePrevPage() {
        if (page > 1) {
            --page;
        } else {
            System.out.println("------------------------");
            System.out.println("| It is the first page!|");
            System.out.println("------------------------");
        }
    }

    public void moveNextPage() throws SQLException, ClassNotFoundException {
        int count = EQuery.getCount();
        int lastPage = count % 10 > 0 ? count / 10 + 1 : count / 10;
        if (page < lastPage) ++page;
        else {
            System.out.println("------------------------");
            System.out.println("| It is the last page! |");
            System.out.println("------------------------");
        }
    }

    public void showDetail() throws IOException, SQLException, ClassNotFoundException {
        EmployeeQuery EQuery = new EmployeeQuery();
        Tool tools = new Tool();
        Employee emp = null;
        System.out.println("Enter Employee ID to see");
        int employeeId = tools.promptNumber();
        emp = EQuery.findEmployee(employeeId);
        if (emp == null) {
            System.out.println("No employee Found");
        } else {
            System.out.println();
            System.out.printf("%-4s   %-22s   %-30s   %-14s   %-11s   %-9s %-20s \n ", " ID", "NAME", "EMAIL", "PHONE", "HIRE_DATE", "MANAGER", "JOB_TITLE");
            System.out.printf("%-3d | %-22s | %-30s | %-14s | %-10s | %-8d | %-20s \n",
                    emp.getEmployeeID(), emp.getFirstName() + " " + emp.getLastName(), emp.getEmail(), emp.getPhone(), emp.getHireDate().toString(),
                    emp.getManagerId(), emp.getJobTitle());
        }
    }
}