package lab10;

import lab09.Employee;
import lab09.HRDateUtils;
import lab09.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest

{
    private Manager manager;
    private ArrayList<Employee> employees;

    @BeforeEach
    void setUp() throws ParseException, Manager.ManagerException
    {
        this.manager = new Manager(0, "Mr", "Crabs", 1112220000, lab10.HRDateUtils.strToDate("1800-12-3") ,0.49, "HR" );
        employees = new ArrayList<>(
                Arrays.asList(
                        new Employee(1, "Spongebob", "Squarepants", 1112229999, lab10.HRDateUtils.strToDate("1933-02-01"),0.49 ),
                        new Employee(10, "Patric", "Star", 1001002000, lab10.HRDateUtils.strToDate("1999-07-19"),0.01 ),
                        new Employee(2, "Squidward", "Tentacles", 110203000, lab10.HRDateUtils.strToDate("1100-05-04"),0.48 ),
                        new Employee(1, "Spongebob", "Squarepants", 1322229999, lab10.HRDateUtils.strToDate("1933-02-01"),0.49 ),
                        new Employee(10, "Patric", "Star", 1651002000, lab10.HRDateUtils.strToDate("1999-07-19"),0.01 ),
                        new Employee(2, "Squidward", "Tentacles", 133203000, lab10.HRDateUtils.strToDate("1100-05-04"),0.48 )
                ));
    }

    @AfterEach
    void tearDown()
    {
    }

    @DisplayName("Manager: Makes sure the wrong department name will throw an error")
    @Test
    void Manager() throws Manager.ManagerException
    {
        //just creating one new class with a bad department name, should throw and error
        assertThrows(Manager.ManagerException.class, () -> new Manager(0, "Mr", "Crabs", 1112220000, new Date(),0.49, "HRV" ));
    }

    @DisplayName("addEmployee: add several employees and makes sure they where added")
    @Test
    void addEmployee() throws Manager.ManagerException
    {
        assertEquals(0, manager.getEmpList().size());

        for (int i = 0; i < employees.size(); i++)
        {
            manager.addEmployee(employees.get(i));
        }

        //makes sure the lists are the same and that the size is the same
        assertEquals(employees.size(), manager.getEmpList().size());
        assertEquals(employees, manager.getEmpList());
    }

    @DisplayName("addEmployeeException: makes sure adding the same employee twice will throw an exception")
    @Test
    void addEmployeeException() throws Manager.ManagerException
    {
        for (int i = 0; i < employees.size(); i++)
        {
            manager.addEmployee(employees.get(i));
        }

        //trying to add one that is already added
        assertThrows(Manager.ManagerException.class, () -> manager.addEmployee(employees.get(0)));
    }

    @DisplayName("removeEmployee: test to make sure added employees are getting removed")
    @Test
    void removeEmployee() throws Manager.ManagerException
    {
        //addemployee to fill up the list without repeating the code
        this.addEmployee();

        //the size when filled
        int currentSize = this.manager.getEmpList().size();

        //remove one random employee, 2 will work
        this.manager.removeEmployee(this.employees.get(2));

        //make sure one is removed, so the original size and the new size will not be equal
        assertNotEquals(currentSize, this.manager.getEmpList().size());

        //removes the rest of the emplyees
        while(this.manager.getEmpList().size() > 0)
            this.manager.removeEmployee(this.manager.getEmpList().get(0));

        //make sure everyone was removed
        assertEquals(0, this.manager.getEmpList().size());
    }

    @DisplayName("removeEmployeeException: test to make sure trying to remove an employee that is not there will throw an exception")
    @Test
    void removeEmployeeException() throws Manager.ManagerException
    {
        //addemployee to fill up the list without repeating the code
        this.addEmployee();

        //remove an employee
        this.manager.removeEmployee(this.employees.get(2));


        //trying to remove him again should throw an error
        assertThrows(Manager.ManagerException.class, () -> this.manager.removeEmployee(this.employees.get(2)));
    }
}