/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 110am
 * Date: 2/18/20
 * Time: 4:47 PM
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: HRDBSystem
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing an HR system
 *
 * @author Christopher Asbrock
 */
public class HRDBSystem
{
    /**
     * displays an employees id and name
     * and if they are a manager or not
     *
     * @param emp - the employee to print
     */
    public static void displayEmployee(Employee emp)
    {
        System.out.println(emp.getEmpID() + ": " +
                emp.getFirstName() + " "
                + emp.getLastName() +
                ((emp instanceof Manager) ? " [MANAGER]" : ""));
    }

    /**
     * displays a full list of employees
     *
     * @param listOfEmps - the list of employees to... list
     */
    public static void displayEmployees(List<Employee> listOfEmps)
    {
        for (int i = 0; i < listOfEmps.size(); i++)
        {
            displayEmployee(listOfEmps.get(i));
        }
    }

    /**
     * diplays a manager, his department, number of employees, and all employees under him
     *
     * @param manager - the manager to list out
     */
    public static void displayManager(Manager manager)
    {
        System.out.println("Manager:        " + manager.getFirstName() + " " + manager.getLastName());
        System.out.println("Department:     " + manager.getDeptName());
        System.out.println("# Employees:    " + manager.getEmpList().size());
        displayEmployees(manager.getEmpList());
    }

    /**
     * main method for basic testing
     *
     * @param args - command line arguments NOT_USED
     */
    public static void main(String[]args) throws Manager.ManagerException
    {
        try
        {

            ArrayList<Employee> employees = new ArrayList<>(
                    Arrays.asList(
                            new Manager(0, "Mr", "Crabs", 1112220000, HRDateUtils.strToDate("1800-12-3") ,0.49, "HR" ),
                            new Employee(1, "Spongebob", "Squarepants", 1112229999, HRDateUtils.strToDate("1933-02-01"),0.49 ),
                            new Employee(10, "Patric", "Star", 1001002000, HRDateUtils.strToDate("1999-07-19"),0.01 ),
                            new Employee(2, "Squidward", "Tentacles", 110203000, HRDateUtils.strToDate("1100-05-04"),0.48 ),
                            new Manager(0, "Mr", "Crabs", 1112220000, HRDateUtils.strToDate("1800-12-3") ,0.49, "STAFF" ),
                            new Employee(1, "Spongebob", "Squarepants", 1112229999, HRDateUtils.strToDate("1933-02-01"),0.49 ),
                            new Employee(10, "Patric", "Star", 1001002000, HRDateUtils.strToDate("1999-07-19"),0.01 ),
                            new Employee(2, "Squidward", "Tentacles", 110203000, HRDateUtils.strToDate("1100-05-04"),0.48 )
                    ));

            ArrayList<Manager> managers = new ArrayList<>();

            for (int i = 0; i < employees.size(); i++)
            {
                if(employees.get(i) instanceof Manager)
                    managers.add((Manager) employees.get(i));
            }

            managers.get(0).addEmployee(employees.get(1));
            managers.get(0).addEmployee(employees.get(2));
            managers.get(0).addEmployee(employees.get(3));


            managers.get(1).addEmployee(employees.get(5));
            managers.get(1).addEmployee(employees.get(6));
            managers.get(1).addEmployee(employees.get(7));

            System.out.println("toString for an Emplyee:\n");
            System.out.println(employees.get(1)); //employee object

            System.out.println("\ndisplayEmployee for and Emplyee:\n");
            displayEmployee(employees.get(1));

            System.out.println("\ndisplayEmployess (all employees):\n");
            displayEmployees(employees);

            for (int i = 0; i < managers.size(); i++)
            {
                System.out.println("");
                displayManager(managers.get(i));
            }


        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}