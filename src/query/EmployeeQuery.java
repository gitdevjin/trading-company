package query;

import table.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class EmployeeQuery extends Query {
    public EmployeeQuery() {
        super();
    }

    public List<Employee> getEmployees(int page) throws ClassNotFoundException, SQLException {

            int start = 1 + (page - 1) * 10;
            int end = page * 10;

            String sql = "SELECT * FROM (SELECT ROWNUM NUM, e.* FROM (SELECT * FROM employees ORDER BY first_name ) e )WHERE NUM BETWEEN ? AND ?";
            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, start);
            st.setInt(2, end);
            ResultSet rs = st.executeQuery();

            List<Employee> employeeList = new ArrayList<>();

            while(rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("FIRST_NAME");
                String lName = rs.getString("LAST_NAME");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date hireDate = rs.getDate("HIRE_DATE");
                int managerId = rs.getInt("MANAGER_ID");
                String jobTitle = rs.getString("JOB_TITLE");

                Employee emp = new Employee(id, fName, lName, email, phone, hireDate, managerId, jobTitle);
                employeeList.add(emp);
            }

            rs.close();
            st.close();
            conn.close();

            return employeeList;
        }
        public int getCount() throws ClassNotFoundException, SQLException {
            int count = 0;
            String sql = "SELECT COUNT(employee_id) count FROM employees";
            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs.next())
                count = rs.getInt("count");

            rs.close();
            st.close();
            conn.close();

            return count;
        }

        public int getMaxID() throws ClassNotFoundException, SQLException {
            int max = 0;
            String sql = "SELECT MAX(employee_id) MAX FROM employees";
            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs.next())
                max = rs.getInt("max");

            rs.close();
            st.close();
            conn.close();

            return max;
        }

        public int insertNewEmployee(Employee employee) throws ClassNotFoundException, SQLException {
            String sql = "Insert into employees (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE, HIRE_DATE, JOB_TITLE) " +
                    "values (?,?,?,?,?,?,?)";

            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, employee.getEmployeeID());
            st.setString(2, employee.getFirstName());
            st.setString(3, employee.getLastName());
            st.setString(4, employee.getEmail());
            st.setString(5, employee.getPhone());
            st.setDate(6, employee.getHireDate());
            st.setString(7, employee.getJobTitle());

            int result = st.executeUpdate();

            st.close();
            conn.close();
            return result;
        };

        public Employee findEmployee(int id) throws ClassNotFoundException, SQLException {
            String sql = "SELECT * From EMPLOYEES WHERE employee_id = ?";

            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            Employee emp = null;

            if(rs.next()) {
                int empId = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("FIRST_NAME");
                String lName = rs.getString("LAST_NAME");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date hireDate = rs.getDate("HIRE_DATE");
                int managerId = rs.getInt("MANAGER_ID");
                String jobTitle = rs.getString("JOB_TITLE");
                emp = new Employee(empId, fName, lName, email, phone, hireDate, managerId, jobTitle);
            }

            return emp;
        }

        public int updateEmployee(Employee employee, int id) throws ClassNotFoundException, SQLException {
            String sql = "UPDATE EMPLOYEES " +
                                    " SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?," +
                                        "PHONE = ?, MANAGER_ID = ?, JOB_TITLE = ?" +
                                        "WHERE EMPLOYEE_ID = ?";

            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, employee.getFirstName());
            st.setString(2, employee.getLastName());
            st.setString(3, employee.getEmail());
            st.setString(4, employee.getPhone());
            st.setInt(5, 1);
            st.setString(6, employee.getJobTitle());
            st.setInt(7, id);

            int result = st.executeUpdate();

            st.close();
            conn.close();
            return result;
        }

        public int deleteEmployee(int id) throws ClassNotFoundException, SQLException {
            String sql = "DELETE EMPLOYEES WHERE employee_id = ?";

            Class.forName(super.getDriver());
            Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            int rs = st.executeUpdate();

            return rs;
        }


}
