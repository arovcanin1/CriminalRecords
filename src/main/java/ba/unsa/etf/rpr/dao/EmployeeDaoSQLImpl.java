package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    public EmployeeDaoSQLImpl() {
        super("Employee");
    }

    @Override
    public Employee getById(int id) throws CriminalRecordsException {
        return null;
    }

    @Override
    public Employee getByUsername(String username) throws CriminalRecordsException {
        return null;
    }

    public Employee row2object(ResultSet rs) throws CriminalRecordsException, SQLException {

        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstName(rs.getString("firstName"));
            employee.setLastName(rs.getString("lastName"));
            employee.setEmail(rs.getString("email"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password"));
            return employee;
        } catch(SQLException e) {
            throw new CriminalRecordsException(e.getMessage());
        }
    }


}