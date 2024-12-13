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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class HRDateUtils
{
    /** Our date formatter to ensure we have a common date */
    static SimpleDateFormat empDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Helper method to parse a date string into a date object. This is
     * really here just to show how to deal with an exception that may
     * be thrown in a method.
     *
     * @param sDate - a date string
     * @return a <code>Date</code> object
     * @throws ParseException if the string cannot be parse correctly.
     */
    static Date strToDate(String sDate) throws ParseException
    {
        return empDateFormatter.parse(sDate);
    }

    /**
     * helper method to take a date object and return it in a string format
     *
     * @param date - the date to extract the string information from
     * @return - the computed string
     */
    static String dateToStr(Date date)
    {
        return empDateFormatter.format(date);
    }
}