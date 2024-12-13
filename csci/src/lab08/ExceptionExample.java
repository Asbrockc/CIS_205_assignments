/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/13/20
 * Time: 12:06 AM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: ExceptionExample
 *
 * Description:
 *
 *     a boring example of exception that I have seen 1000 times before
 *
 * ****************************************
 */
package lab08;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionExample
{
    public static final int SIZE = 10;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int[] x = new int[SIZE];
        int i = 0;

        do
        {
            try
            {
                System.out.print("Enter an index to fill, or " + SIZE + " to finish: ");
                i = in.nextInt();
                if (i != SIZE)
                    x[i] = i;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println(i + " is out of range! Try again!");
                continue;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter integers! Try again!");
                in.nextLine();
                continue;
            }
        } while (i != SIZE);
    }
}