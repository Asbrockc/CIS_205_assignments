/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 *
 * Name: Christopher Asbrock
 * Date: Feb 18 2020
 * Time: 11:00 pm
 *
 * Project: csci205
 * Package: lab09
 * File: Employee
 * Description:
 * A very basic abstraction for an employee in a simple HR system
 * ****************************************
 */
package lab10;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

import static lab10.HRDateUtils.dateToStr;

/**
 * A basic representation for an Employee to be stored in an HR database system
 *
 * @author Brian King
 * @author Christopher Asbrock
 */
public class Employee extends Person implements Payable
{

    /**normal work week , in hours */
    private final double WORK_WEEK = 40;

    /**weeks in a year*/
    private final double WEEKS_PER_YEAR = 52;

    /**time and half for overtime*/
    private final double OVERTIME = 1.5;

    /** Date employee was hired */
    private Date hireDate;

    /** Current salary of the employee */
    private double salary;

    /**
     * Explicit constructor to create new employee
     *
     * @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link Date} object
     * @param salary    Current employee salary
     */
    public Employee(int empID, String firstName, String lastName, int ssNum, Date hireDate, double salary)
    {
        super(empID, firstName, lastName, ssNum);
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * @return {@link Date} employee was hired
     */
    public Date getHireDate()
    {
        return hireDate;
    }

    /**
     * @return current employee salary
     */
    public double getSalary()
    {
        return salary;
    }

    /**
     * Raise the salary by <code>salaryAdj</code> dollars.
     *
     * @param salaryAdj - amount to add to the current salary
     * @return the new salary
     */
    public double raiseSalary(double salaryAdj)
    {
        this.salary += salaryAdj;
        return this.salary;
    }

    /**
     * Return a string representation of the Employee
     *
     * @return the String of comma delimited values
     */
    @Override
    public String toString()
    {
        String s = this.empID + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += "," + dateToStr(this.hireDate);
        s += String.format(",%.2f", this.salary);
        return s;
    }

    /**
     * equals override to compare two employees based on SS#
     *
     * @param o - other object to compare
     * @return - true if the SS# is the same; false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        return ssNum == ((Employee) o).ssNum;

    }

    /**
     * returns the hashcode based on class values
     *
     * @return - computed hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(empID, firstName, lastName, ssNum, hireDate, salary);
    }

    /**
     * calculates pay for an employee
     * normal week 40 hours -
     * annual pay / 52 weeks / 40 hours = hourly pay
     *
     * anything over 40 hours is time and a half
     *
     * @param numHrs - hours worked
     * @return - calculated pay
     */
    @Override
    public double calculatePay(double numHrs)
    {
        double payPerHour = ((this.salary) / (WEEKS_PER_YEAR)) / (WORK_WEEK);

        if (numHrs <= WORK_WEEK)
            return (numHrs * payPerHour);
        else
        {
            double totalPay = (WORK_WEEK * payPerHour);
            totalPay += (numHrs - WORK_WEEK) * (payPerHour * OVERTIME);
            return totalPay;
        }
    }

    /**
     * gets the employees full name
     *
     * @return - calculated string
     */
    @Override
    public String getPayTo()
    {
        return (this.firstName + " " + this.lastName);
    }

    /**
     * the pay memo
     * with name, ID, and paydate
     *
     * @return - computed String
     */
    @Override
    public String getPayMemo()
    {
        return ("Employee ID: "+
                this.empID +
                ", Pay Date: " +
                dateToStr(new Date()));
    }

}

