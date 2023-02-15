package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws CriminalRecordsException {

        System.out.println("Welcome to CR!");

        System.out.println("For login type L, For registration type R");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("L")) {
            String username;
            String password;
            System.out.println("Enter username: ");
            Scanner newScaner = new Scanner(System.in);
            username = newScaner.next();
            System.out.println("Enter password: ");
            password = scanner.next();
            Employee employee = DaoFactory.employeesDao().getByUsername(username);

            for (;;) {
                // If login is successful
                if (employee != null && employee.getPassword().equals(password)) {
                    System.out.println("Success!");
                    break;
                }

                if (employee == null) {
                    System.out.println("Enter username again: ");
                    String repeatUsername;
                    Scanner repeatScaner = new Scanner(System.in);
                    repeatUsername = repeatScaner.next();
                }

                if (!employee.getPassword().equals(password)) {
                    System.out.println("Enter password again: ");
                    String repeatUsername;
                    Scanner repeatScaner = new Scanner(System.in);
                    repeatUsername = repeatScaner.next();
                }

                showEmployee(employee.getId());
            }
        }

        if (scanner.next().equals("R")) {
            String firstName;
            String lastName;
            String username;
            String email;
            String password;
            String confirmPassword;
            System.out.println("Enter name: ");
            Scanner enterName = new Scanner(System.in);
            firstName = enterName.next();
            System.out.println("Enter surname: ");
            Scanner enterSurname = new Scanner(System.in);
            lastName = enterSurname.next();
            Scanner enterUsername = new Scanner(System.in);
            username = enterUsername.next();
            Scanner enterEmail = new Scanner(System.in);
            email = enterEmail.next();
            Scanner enterPassword = new Scanner(System.in);
            password = enterPassword.next();
            Scanner enterPasswordAgain = new Scanner(System.in);
            confirmPassword = enterPasswordAgain.next();

            Employee e = new Employee();
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setUsername(username);
            e.setPassword(password);
            EmployeeManager.add(e, confirmPassword);
            showEmployee(e.getId());
        }
    }

    public static void showEmployee(int id)  {
        System.out.println("You have following options: ");
        System.out.println("");
    }
}
