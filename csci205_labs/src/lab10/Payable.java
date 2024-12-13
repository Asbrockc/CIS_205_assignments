/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/18/20
 * Time: 6:37 PM
 *
 * Project: csci205_labs
 * Package: lab09
 * Class: HRDateUtils
 *
 * Description:
 *
 * ****************************************
 */
package lab10;

/**
 * interface to define needed pay methods
 */
public interface Payable
{
    /**
     * calculates pay
     *
     * @param numHrs - hours worked
     * @return - calculated pay
     */
    public double calculatePay(double numHrs);

    /**
     * gets who the pay is for
     *
     * @return - the name of the payee
     */
    public String getPayTo();

    /**
     * gets the pay memo
     *
     * @return - the pay memo
     */
    public String getPayMemo();
}
