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
package lab10;

import javax.swing.*;
import java.text.ParseException;
import java.util.*;

/**
 * Class representing an HR system
 *
 * @author Christopher Asbrock
 */
public class HRDBSystem
{

    /**a list for employees*/
    private static ArrayList<Employee> employees;
    /**a list for managers*/
    private static ArrayList<Manager> managers;
    /**a list for contractors*/
    private static ArrayList<Contractor> contractors;

    /**
     * displays an employees id and name
     * and if they are a manager or not
     *
     * @param emp - the employee to print
     */
    public static void displayEmployee(Employee emp)
    {
        System.out.println(emp.getID() + ": " +
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
     * displays a manager, his department, number of employees, and all employees under him
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
     * A test method that will take every arraylist of any type of employee and sort it by ame and ID
     */
    private static void sortEmployees()
    {
        System.out.println("*******BEFORE SORT*******:");
        printAllLists();

        //sorts all 3 lists
        sort(employees);
        sort(managers);
        sort(contractors);

        System.out.println("*******AFTER SORT*******:");
        printAllLists();
    }

    /**
     * prints out the lists of all 3 types of employee lists
     */
    private static void printAllLists()
    {
        System.out.println("EMPLOYEES:");
        displayEmployees(employees);
        System.out.println("MANAGERS:");
        displayList(managers);
        System.out.println("CONTRACTORS:");
        displayList(contractors);
    }

    /**
     * takes the contents of any list and prints them out
     *
     * @param list - the list to print out
     */
    private static void displayList(List list)
    {
        Iterator iterator = list.iterator();

        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    /**
     * uses collections to sort any list by last name and ID
     *
     * @param list - the list that needs to be sorted
     */
    private static void sort(List list)
    {
        Collections.sort(list, (one, two)->
        {
            int compare = ((Person)one).getLastName().compareTo(((Person)two).getLastName());
            if (compare != 0)
                return compare;
            return -(((Person)one).getID() - ((Person)two).getID());
        });
    }

    /**
     * displays all contractors currently in the list
     */
    private static void displayContractorTests()
    {
        for (int i = 0; i < contractors.size(); i++)
        {
            System.out.println(contractors.get(i));
        }
    }

    /**
     * displays all managers currently in the list
     */
    private static void displayManagerTests()
    {
        for (int i = 0; i < managers.size(); i++)
        {
            System.out.println("");
            displayManager(managers.get(i));
        }
    }

    /**
     * tests the tostring and display employees methods
     */
    private static void displayEmployeeTests()
    {
        System.out.println("toString for an Emplyee:\n");
        System.out.println(employees.get(1)); //employee object

        System.out.println("\ndisplayEmployee for and Emplyee:\n");
        displayEmployee(employees.get(1));

        System.out.println("\ndisplayEmployess (all employees):\n");
        displayEmployees(employees);
    }

    /**
     * initializes an array of random contractors
     */
    private static void initContractors() {
        contractors = new ArrayList<>(
                Arrays.asList(
                    new Contractor(73, "Builder", "Bob", 342942039, 30.00),
                    new Contractor(100, "Trump", "Donald", 1000000000, 300.00)
                ));
    }

    /**
     * A test to test the Accounts class ability to use process check between two different classes
     */
    private static void testAccountMethods()
    {
        // Create an account
        Account acc = new Account(4000.0);
        System.out.println(acc);
        // Test out a couple of payments, intentionally throwing an exception
        // with the second payment
        try
        {
            System.out.println("TEST: Printing a check to employee id: "
                    + employees.get(0).getID());
             // 40 hrs + 10 hrs overtime
            acc.processCheck(employees.get(0), 50, System.out);
            System.out.println("TEST: Printing a check to contractor id: "
                    + contractors.get(0).getID());
            acc.processCheck(contractors.get(0), 35, System.out);
        }
        catch (InsufficientFundsException e)
        {
            System.out.println(e.getMessage());
        }
        // Verify that funds were debited from the account
        System.out.println(acc);
    }

    /**
     * creates a list of managers from the list of all employees
     */
    private static void initManagers()
    {
        managers = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++)
        {
            if(employees.get(i) instanceof Manager)
                managers.add((Manager) employees.get(i));
        }

    }

    /**
     * assigns employees to the managers on the list
     * 2 managers, 3 employees each
     *
     * @throws Manager.ManagerException - if the employee has already in the list
     */
    private static void assignEmployeesToManagers() throws Manager.ManagerException
    {
        managers.get(0).addEmployee(employees.get(1));
        managers.get(0).addEmployee(employees.get(2));
        managers.get(0).addEmployee(employees.get(3));

        managers.get(1).addEmployee(employees.get(5));
        managers.get(1).addEmployee(employees.get(6));
        managers.get(1).addEmployee(employees.get(7));
    }

    /**
     * creates a list of employees
     * 
     * @throws Manager.ManagerException - if manager has wrong department
     * @throws ParseException - if date formatting is wrong
     */
    private static void initEmployees() throws Manager.ManagerException, ParseException
    {
        employees = new ArrayList<>
                (//initialized with an array of new types of employees
                Arrays.asList(
                        new Manager(0, "Mr", "Crabs", 1112220000, HRDateUtils.strToDate("1800-12-3") ,100000, "HR" ),
                        new Employee(1, "Spongebob", "Squarepants", 1112229999, HRDateUtils.strToDate("1933-02-01"),3000 ),
                        new Employee(10, "Patric", "Star", 1001002000, HRDateUtils.strToDate("1999-07-19"),3000 ),
                        new Employee(2, "Squidward", "Tentacles", 110203000, HRDateUtils.strToDate("1100-05-04"),3000 ),
                        new Manager(0, "Mr", "Crabs", 1112220000, HRDateUtils.strToDate("1800-12-3") ,3000, "STAFF" ),
                        new Employee(1, "Spongebob", "Squarepants", 1112229999, HRDateUtils.strToDate("1933-02-01"),3000 ),
                        new Employee(10, "Patric", "Star", 1001002000, HRDateUtils.strToDate("1999-07-19"),3000 ),
                        new Employee(2, "Squidward", "Tentacles", 110203000, HRDateUtils.strToDate("1100-05-04"),3000 )
                ));
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
            //init all list, and employees under manager
            initEmployees();
            initManagers();
            initContractors();
            assignEmployeesToManagers();

            //test displays
            displayEmployeeTests();
            displayManagerTests();
            displayContractorTests();

            //test for account
            testAccountMethods();

            //sort and display sorted lists
            sortEmployees();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}