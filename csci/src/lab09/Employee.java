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
package lab09;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

/**
 * A basic representation for an Employee to be stored in an HR database system
 *
 * @author Brian King
 * @author Christopher Asbrock
 */
public class Employee
{

    /** Employee id */
    private int empID;

    /** First name */
    private String firstName;

    /** Last name */
    private String lastName;

    /** Social Security number */
    private int ssNum;

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
        this.empID = IDFactory.safeToUse(Integer.valueOf(empID));
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * @return the employee id
     */
    public int getEmpID()
    {
        return empID;
    }

    /**
     * @return employee first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return Last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return Social Security number
     */
    public int getSsNum()
    {
        return ssNum;
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
     * Change the name of the employee
     *
     * @param first - New first name
     * @param last - New last name
     */
    public void changeName(String first, String last)
    {
        this.firstName = first;
        this.lastName = last;
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
        s += "," + HRDateUtils.dateToStr(this.hireDate);
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
     * factory class to generate unique IDs in a safe way
     */
    private static class IDFactory
    {

        /**Callection of unique Employee IDs generated / assigned */
        private static HashSet<Integer> setOfAssignedIDs = new HashSet<>();

        /**
         * generates an ID based on IDs already logged into the system
         *
         * @return - generated ID
         */
        private static Integer generateID()
        {
            //if the set size is 0 there is no IDs used... so 1 will work
            if (setOfAssignedIDs.size() == 0)
                return 1;

            Integer base = 1;

            for (int i = 1; i < setOfAssignedIDs.size() + 1; i++)
            {
                base = i;

                if (!setOfAssignedIDs.contains(base))
                    return base;
            }

            //plus 1 becuase it went through the entire set and every value was taken so this is one more
            return base + 1;
        }


        /**
         * helper method for constructor
         * checks the given ID to see if it taken and generates it if needed
         *
         * @param empID - the requested ID value
         */
        private static int safeToUse(Integer empID)
        {
            if (empID < 1 || setOfAssignedIDs.contains(Integer.valueOf(empID)))
            {
                Integer newID = generateID();
                setOfAssignedIDs.add(newID);
                return newID.intValue();
            }
            else
            {
                setOfAssignedIDs.add(Integer.valueOf(empID));
                return empID.intValue();
            }
        }
    }
}

