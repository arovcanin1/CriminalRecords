package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for EmployeeManager class
 */
public class EmployeeManagerTest {
    Employee employee = new Employee();

    @BeforeEach
    public  void setEmployee() {
        employee.setId(1);
        employee.setFirstName("Amila");
        employee.setLastName("Rovcanin");
        employee.setUsername("arovcanin1");
        employee.setEmail("arovcanin1@etf.unsa.ba");
        employee.setPassword("someBadPass");
    }

    @Test
    public void exceptionTest() {
        try {
            DaoFactory.employeesDao().add(employee);
        } catch (CriminalRecordsException e) {
            assertEquals("Password is not correct!", e.getMessage());
            e.printStackTrace();
        }
    }
}
