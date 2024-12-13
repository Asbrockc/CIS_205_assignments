/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/18/20
 * Time: 4:40 PM
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: Manager
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * manager class extending employee
 * adds the store section they manage
 *
 * @author Christopher Asbrock
 */
public class Manager extends Employee
{


    /**name of the mangers department*/
    private DepartmentName deptName;

    /**arraylist to hold employees under this manager*/
    private ArrayList<Employee> employees;

    /**
     * Explicit constructor to create new Manager
     *
     * @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link Date} object
     * @param salary    Current employee salary
     * @param deptName  The (@link String) this manager is the manager of
     */
    public Manager(int empID, String firstName, String lastName, int ssNum, Date hireDate, double salary, String deptName) throws ManagerException
    {
        super(empID, firstName, lastName, ssNum, hireDate, salary);
        this.employees = new ArrayList<>();

        try
        {
            this.deptName = DepartmentName.valueOf(deptName.toUpperCase());
        }
        catch (IllegalArgumentException e)
        {
            throw new ManagerException("Invalid deptName: " + deptName);
        }
    }

    /**
     * Explicit constructor to create new Manager with DepartmentName
     *
     *  @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link Date} object
     * @param salary    Current employee salary
     * @param deptName  The (@link DepartmentName) this manager is the manager of
     */
    public Manager(int empID, String firstName, String lastName, int ssNum, Date hireDate, double salary, DepartmentName deptName)
    {
        super(empID, firstName, lastName, ssNum, hireDate, salary);
        this.employees = new ArrayList<>();
        this.deptName = deptName;
    }

    /**
     * toString for managers, employee toString + the department they manage
     *
     * @return - formed string
     */
    @Override
    public String toString()
    {
        String message = super.toString();
        message += ", " + this.deptName;

        return message;
    }

    /**
     * adds an employee to the managers list of employees
     *
     * @param employee - the employee to add
     * @throws ManagerException - if the employee is already in the list
     */
    public void addEmployee(Employee employee) throws ManagerException
    {
        int employeeSpot = searchEmployeeList(employee);

        if (employeeSpot != -1)
            throw new ManagerException("ERROR: " +
                    employee.getFirstName() + " "
                    + employee.getLastName() + " "
                    +"is already in the List");

        this.employees.add(employee);
    }

    /**
     * searches the list for an employee then returns his spot in the list
     *
     * @param employee - the employee to search for
     * @return - the index of the employee (or -1 is they are not in it)
     */
    private int searchEmployeeList(Employee employee)
    {
        int employeeSpot = -1;
        //checks to make sure they are not already in it
        for (int i = 0; i < this.employees.size(); i++)
        {
            if (employee.equals(this.employees.get(i)))
            {
                employeeSpot = i;
                break;
            }
        }
        return employeeSpot;
    }

    /**
     * returns a list of the employees under this manager
     *
     * @return - List of Employees
     */
    public List<Employee> getEmpList()
    {
        return this.employees;
    }

    /**
     * searches employee list for an employee and removes him
     *
     * @param employee - employee to remove
     * @throws ManagerException - if they are not in the list
     */
    public void removeEmployee(Employee employee) throws ManagerException
    {
        int employeeIndex = this.searchEmployeeList(employee);

        if (employeeIndex == -1)
            throw new ManagerException("ERROR: " +
                    employee.getFirstName() + " "
                    + employee.getLastName() + " "
                    +"is NOT in List");

        this.employees.remove(employeeIndex);
    }

    /**
     * return the name of the managers department
     *
     * @return - department name
     */
    public DepartmentName getDeptName()
    {
        return deptName;
    }

    /**
     * change the managers department name
     *
     * @param deptName - the new department
     */
    public void setDeptName(DepartmentName deptName)
    {
        this.deptName = deptName;
    }

    /**
     * Checked exception representing any issues that might arise from the Manager class
     */
    public class ManagerException extends Exception
    {
        public ManagerException(String message)
        {
            super(message);
        }
    }
}

/**
 * enum representing managers departments
 * outside of the class so others in the package can use it as well
 *
 * @author Christopher Asbrock
 */
enum DepartmentName
{
    /**values representing manager departments*/
    ENGINEERING("Engineering"), HR("HR"), ADMIN("Admin"), STAFF("Staff"), OTHER ("Other");

    /**holds the printable string value*/
    private String departmentName;

    /**
     * default constructor
     */
    DepartmentName()
    {
        this.departmentName = "";
    }

    /**
     * constructor to set a printable name for each department
     *
     * @param dname - department name
     */
    DepartmentName(String dname)
    {
        this.departmentName = dname;
    }

    /**
     * getter for department name
     *
     * @return - this department Name
     */
    public String getValue()
    {
        return this.departmentName;
    }
}
