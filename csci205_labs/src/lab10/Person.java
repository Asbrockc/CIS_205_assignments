/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 2/23/20
 * Time: 1:14 AM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: Person
 *
 * Description:
 *
 * ****************************************
 */
package lab10;

import java.util.HashSet;

/**
 * abstract class to take care of the basic fields each type of worker has as well
 * as getter setters... because frankly its really dumb to not do this and type everything twice
 *
 * I reject your lab instructions and substitute my own...
 */
public abstract class Person
{
    /** Employee id */
    protected int empID;
    /** First name */
    protected String firstName;
    /** Last name */
    protected String lastName;
    /** Social Security number */
    protected int ssNum;

    /**
     * constructor to initialize basic fields
     *
     * @param empID - id number
     * @param firstName - first name
     * @param lastName - last name
     * @param ssNum - social security number
     */
    public Person(int empID, String firstName, String lastName, int ssNum)
    {
        this.empID = IDFactory.safeToUse(Integer.valueOf(empID));
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
    }

    /**
     * @return the employee id
     */
    public int getID()
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
     * factory class to generate unique IDs in a safe way
     */
    static class IDFactory
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
        static int safeToUse(Integer empID)
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