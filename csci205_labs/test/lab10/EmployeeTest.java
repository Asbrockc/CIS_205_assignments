package lab10;

import lab10.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest
{

    /**or tests that use doubles to bypass float point inaccuracies*/
    private static final double EPSILON = 1.0E-12;

    /**base emplyee for testing*/
    private Employee emp;

    @BeforeEach
    void setUp() throws ParseException
    {
        emp =      new Employee(1,
                "Spongebob",
                "Squarepants",
                1112229999,
                lab10.HRDateUtils.strToDate("1933-02-01"),
                10 * 52 * 40); // should be $10 an hour
    }

    @AfterEach
    void tearDown()
    {
    }

    @DisplayName("changeName: test to make sure names are correctly changed")
    @Test
    void changeName()
    {
        String first = this.emp.getFirstName();
        String last = this.emp.getLastName();

        this.emp.changeName("Bob", "Saget");

        assertNotEquals(first, this.emp.getFirstName());
        assertNotEquals(last, this.emp.getLastName());
    }

    @DisplayName("raiseSalary: test to make sure salary is raised")
    @Test
    void raiseSalary()
    {
        double startSalary = this.emp.getSalary();
        double raiseInSalary = 0.1;

        this.emp.raiseSalary(raiseInSalary);

        //makes sure its diffent then start
        assertNotEquals(startSalary, this.emp.getSalary());
        //make sure its the correct amount
        assertEquals((startSalary + raiseInSalary), this.emp.getSalary(), EPSILON);
    }

    @DisplayName("equals: test to make sure two Employees are correctly compared")
    @Test
    void equals() throws ParseException
    {
        Employee employeeTwo =      new Employee(1,
                "Spongebob",
                "Squarepants",
                1112229999,
                lab10.HRDateUtils.strToDate("1933-02-01"),
                0.49 );
        //should be the same off the bat since they have the same constructor
        assertTrue(this.emp.equals(employeeTwo));

        //make sure its only looking at SS#
        employeeTwo.changeName("someone","else");
        employeeTwo.raiseSalary(0.1);

        assertTrue(this.emp.equals(employeeTwo));

        //finally recreate it with a new ss#, then make sure its different
        employeeTwo =      new Employee(1,
                "Spongebob",
                "Squarepants",
                1102229999,
                lab10.HRDateUtils.strToDate("1933-02-01"),
                0.49 );

        assertFalse(this.emp.equals(employeeTwo));

    }

    @DisplayName("calculatePay: test to make sure pay is payed correctly")
    @Test
    void calculatePay()
    {
        double payPerHour = 10;
        double payPerWeek = 400;
        double paySixtyHourWeek = 400/*weeks pay*/ + 300/*overtime pay*/;

        //makes sure pay per hour is right
        assertEquals(payPerHour, this.emp.calculatePay(1), EPSILON);

        //makes sure pay per a 40 hour week
        assertEquals(payPerWeek, this.emp.calculatePay(40), EPSILON);

        //full 40 hour week + 20 overtime
        assertEquals(paySixtyHourWeek, this.emp.calculatePay(60), EPSILON);
    }
}