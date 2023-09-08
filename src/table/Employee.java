package table;

import java.util.Date;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private int managerId;
    private String jobTitle;

    /*** Constructor ***/
    public Employee(){

    }

    public Employee(int employeeID, String firstName, String lastName, String email,
                    String phone, Date hireDate, int managerId, String jobTitle)
    {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.managerId = managerId;
        this.jobTitle = jobTitle;
    }

    /*** Getter and Setter ***/
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getHireDate() {
        return (java.sql.Date) hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
