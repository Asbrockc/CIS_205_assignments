/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/23/20
 * Time: 1:09 AM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: Contractor
 *
 * Description:
 *
 * ****************************************
 */
package lab10;

import java.util.Date;

import static lab10.HRDateUtils.dateToStr;

/**
 * class representing a contractor
 * inherits person class for basic fields
 */
public class Contractor extends Person implements Payable
{
    /**hourly wage for contractors*/
    private double hourlyRate;

    /**
     * constructor
     *
     * @param empID - employee ID (I guess temp)
     * @param firstName - first name
     * @param lastName - last name
     * @param ssNum - social security number
     * @param hourlyRate - hourly rate of pay
     */
    public Contractor(int empID, String firstName, String lastName, int ssNum, double hourlyRate)
    {
        super(empID, firstName, lastName, ssNum);
        this.hourlyRate = hourlyRate;
    }

    /**
     * calculates pay from hours worked
     * contractors is just rate * hours
     *
     * @param numHrs - hours worked
     * @return - calculated pay
     */
    @Override
    public double calculatePay(double numHrs)
    {
        return numHrs * this.hourlyRate;
    }

    /**
     * return the full name of the contractor
     *
     * @return - computed name
     */
    @Override
    public String getPayTo()
    {
        return (this.firstName + " " + this.lastName);
    }

    /**
     * memo noting ID, name, and date of pay
     *
     * @return - computed string
     */
    @Override
    public String getPayMemo()
    {
        return ("Contractor ID: "+
                this.empID +
                ", Pay Date: " +
                dateToStr(new Date()));
    }

    /**
     * Return a string representation of the Constructor
     *
     * @return the String of comma delimited values
     */
    @Override
    public String toString()
    {
        String s = "CONTRACTOR:";
        s += this.empID + ", " + this.lastName + "," + this.firstName;
        s += String.format(", %09d", this.ssNum);
        s += String.format(", %.2f", this.hourlyRate);
        return s;
    }
}