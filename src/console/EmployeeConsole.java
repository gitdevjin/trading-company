package console;

import button.EmployeeButton;
import query.EmployeeQuery;
import table.Employee;
import utility.Tool;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeConsole extends Console {
    public void displayMainMenu() throws IOException, SQLException, ClassNotFoundException {
        EXIT:
        while(true){
            screen();
            int select = selectMenu();
            switch (select){
                case 1:
                    displayEmployee();
                    break;
                case 2:
                    addNewEmployee();
                    break;
                case 3:
                    updateEmployeeInfo();
                    break;
                case 4:
                    deleteEmployeeData();
                    break;
                case 0:
                    System.out.println();
                    System.out.println();
                    break EXIT;
            }
        }
    }

    private void screen(){
        System.out.println("=--------------------------------------=");
        System.out.println("|                                      |");
        System.out.println("|         Employee Management          |");
        System.out.println("|                                      |");
        System.out.println("=--------------------------------------=");
        System.out.println();
        System.out.println("========================================");
        System.out.println("1. Display Employee");
        System.out.println("2. Add Employee");
        System.out.println("3. Update Employee Info");
        System.out.println("4. Delete Employee");
        System.out.println("0. Go Back");
        System.out.println();
        System.out.println("========================================");
    }

    public int selectMenu() throws IOException {
        Tool tools = new Tool();
        int result;
        while(true) {
            result = tools.promptNumber();
            if(result >= 0 && result <= 4){
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        return result;
    }

    private void displayEmployee() throws SQLException, ClassNotFoundException, IOException {
        EmployeeButton button = new EmployeeButton();
        button.printEmployee();
        EXIT:
        while(true){
            int select = selectMenu();
            switch (select){
                case 1:
                    button.printEmployee();
                    break;
                case 2:
                    button.movePrevPage();
                    button.printEmployee();
                    break;
                case 3:
                    button.moveNextPage();
                    button.printEmployee();
                    break;
                case 4:
                    button.showDetail();
                    break;
                case 0:
                    System.out.println();
                    System.out.println();
                    break EXIT;
            }
        }
    };

    private void addNewEmployee() throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        EmployeeQuery EQuery = new EmployeeQuery();
        Tool tools = new Tool();
        Employee emp = new Employee();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime());

        System.out.println("Adding New Employee");
        emp.setEmployeeID(EQuery.getMaxID() + 1);
        System.out.println("Enter New Employee's First Name");
        emp.setFirstName(tools.promptWord());
        System.out.println("Enter Last Name");
        emp.setLastName(tools.promptWord());
        System.out.println("Enter Email Address (example123@gmail.com)");
        emp.setEmail(tools.promptWord());
        emp.setHireDate(today);
        System.out.println("Enter Phone Number Without '-' or '.'");
        emp.setPhone(tools.promptWord());
        System.out.println("Enter Job Title");
        System.out.printf(">>> ");
        emp.setJobTitle(scan.nextLine());

        if(EQuery.insertNewEmployee(emp) == 1) {
            System.out.println("---------------------------");
            System.out.println("-    New Employee Added   -");
            System.out.println("---------------------------");
        } else {
            System.out.println("Add Failed");
        }

    }

    private void updateEmployeeInfo() throws IOException, SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        EmployeeQuery EQuery = new EmployeeQuery();
        Tool tools = new Tool();
        Employee emp = null;

        System.out.println("Updating New Employee");
        System.out.println("Enter Employee ID to update");
        int employeeId = tools.promptNumber();
        emp = EQuery.findEmployee(employeeId);
        if (emp == null){
            System.out.println("No employee Found");
        } else {
            System.out.printf("%-4s   %-22s   %-30s   %-14s   %-11s   %-9s %-20s \n ", " ID", "NAME", "EMAIL", "PHONE", "HIRE_DATE", "MANAGER", "JOB_TITLE");
            System.out.printf("%-3d | %-22s | %-30s | %-14s | %-10s | %-8d | %-20s \n",
                    emp.getEmployeeID(), emp.getFirstName() +" "+ emp.getLastName(), emp.getEmail(), emp.getPhone(), emp.getHireDate().toString(),
                    emp.getManagerId(), emp.getJobTitle());

            System.out.println("You want to UPDATE the information? (y/n)");
            String input = tools.promptWord();
            if (input.equals("y") || input.equals("Y")){
                System.out.println("Enter Employee's First Name");
                emp.setFirstName(tools.promptWord());
                System.out.println("Enter Last Name");
                emp.setLastName(tools.promptWord());
                System.out.println("Enter Email Address (example123@gmail.com)");
                emp.setEmail(tools.promptWord());
                System.out.println("Enter Phone Number Without '-' or '.'");
                emp.setPhone(tools.promptWord());
                System.out.println("Enter Job Title");
                System.out.printf(">>> ");
                emp.setJobTitle(scan.nextLine());

                EQuery.updateEmployee(emp, employeeId);
                System.out.println("---------------------------");
                System.out.println("-    Employee Updated     -");
                System.out.println("---------------------------");
            } else {
                System.out.println("Update Canceled");
            }

        }
    }

    private void deleteEmployeeData() throws IOException, SQLException, ClassNotFoundException {
        EmployeeQuery EQuery = new EmployeeQuery();

        Tool tools = new Tool();
        Employee emp = null;
        System.out.println("Updating New Employee");
        System.out.println("Enter Employee ID to delete");
        int employeeId = tools.promptNumber();
        emp = EQuery.findEmployee(employeeId);
        if (emp == null){
            System.out.println("No employee Found");
        } else {
            System.out.printf("%-4s   %-22s   %-30s   %-14s   %-11s   %-9s %-20s \n ", " ID", "NAME", "EMAIL", "PHONE", "HIRE_DATE", "MANAGER", "JOB_TITLE");
            System.out.printf("%-3d | %-22s | %-30s | %-14s | %-10s | %-8d | %-20s \n",
                    emp.getEmployeeID(), emp.getFirstName() +" "+ emp.getLastName(), emp.getEmail(), emp.getPhone(), emp.getHireDate().toString(),
                    emp.getManagerId(), emp.getJobTitle());

            System.out.println("You want to DELETE the information? (y/n)");
            String input = tools.promptWord();
            if (input.equals("y") || input.equals("Y")){
                if (EQuery.deleteEmployee(employeeId) == 1) {
                    System.out.println("---------------------------");
                    System.out.println("-    Employee Deleted     -");
                    System.out.println("---------------------------");
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("---------------------------");
                System.out.println("-   Deletion Canceled     -");
                System.out.println("---------------------------");
            }
        }
    }



}
