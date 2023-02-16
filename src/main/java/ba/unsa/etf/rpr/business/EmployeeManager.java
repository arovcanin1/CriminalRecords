package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

/**
 * Class that is business layer for Employees
 * Contains methods for checking all actions for login and registration
 */
public class EmployeeManager {

    /**
     * Method that adds Employee if username and password are correct
     * @param employee
     * @param confirmPassword
     * @return
     * @throws CriminalRecordsException
     */
    public static Employee add(Employee employee, String confirmPassword) throws CriminalRecordsException {

        // Checking if all fields are not null
        if (employee.getFirstName().equals(null) ||
            employee.getLastName().equals(null) ||
            employee.getEmail().equals(null) ||
            employee.getUsername().equals(null) ||
            employee.getPassword().equals(null) ||
            confirmPassword.equals(null)) {
            throw new CriminalRecordsException("Each field must be filled!");
        }

        if (employee.getId() != 0) {
            throw new CriminalRecordsException("ID is autogenerated");
        }

        // Checking if password contains more than 8 characters, numbers and special character
        if (employee.getPassword().length() < 8 &&
            !(employee.getPassword().contains("0") ||
              employee.getPassword().contains("1") ||
              employee.getPassword().contains("2") ||
              employee.getPassword().contains("3") ||
              employee.getPassword().contains("4") ||
              employee.getPassword().contains("5") ||
              employee.getPassword().contains("6") ||
              employee.getPassword().contains("7") ||
              employee.getPassword().contains("8") ||
              employee.getPassword().contains("9")) &&
            !(employee.getPassword().contains("&") || employee.getPassword().contains("-"))) {
            throw new CriminalRecordsException("Password must contain at least eight characters, number and special character");
        }

        // Checking if username or email already exist
        try {
            return DaoFactory.employeesDao().add(employee);
        } catch (CriminalRecordsException e) {
            if (e.getMessage().contains("email")) {
                throw new CriminalRecordsException("Employee with same email already exist!");
            }
            if (e.getMessage().contains("username")) {
                throw new CriminalRecordsException("Employee with same username already exist!");
            }
            throw e;
        }
    }

    /**
     * Method that allows Employee to login only if username and password are correct
     * @param username
     * @param password
     * @return
     * @throws CriminalRecordsException
     */
    public Employee loginSearch(String username, String password) throws CriminalRecordsException {

        Employee employee = DaoFactory.employeesDao().getByUsername(username);
        if (username == null || password.equals("")) {
            throw new CriminalRecordsException("Each field must be filled!");
        }

        if (!employee.getPassword().equals(password)) {
            throw new CriminalRecordsException("Password is not correct!");
        }

        return employee;
    }

}
