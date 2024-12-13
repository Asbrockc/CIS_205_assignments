/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2020
* Instructor: Christopher Dancy
* Section: 11:00am - 11:52am
*
* Name: Christopher Asbrock
* Date: 01/27/2020
*
* Lab / Assignment: Lab03
*
* Description: A program that will calculate a Fibonacci sequence with a loop
* and recursive
* *****************************************/

package lab03;

import java.util.Scanner;

/**
 * A simple class designed to give the user an opportunity to test a recursive
 * and non-recursive version of fibonacci
 *
 * Date: 2020-spring
 *
 * @author
 */
public class Fibonacci
{

    /**
     * Compute the nth fibonacci number recursively
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int recFib(int n)
    {
        //two base cases for 1 and 0 to end the recurtion
        if (n == 0)
          return 0;
        else if (n == 1)
          return 1;
        else
          return recFib(n - 2) + recFib(n - 1);
    }

    /**
     * Compute the nth fibonacci number non-recursively, using a while loop.
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int nonRecFib(int n)
    {
        //a few various to hold the sequence as it goes along
        int total = 0;
        int previous = 0;
        int next = 0;
        int temp = 0;
        int count = 0;

        while (true)
        {
            /*feel like a for loop would have made it easier to stack this up (well this is what
            was in my head at the time of writing this anyway)... but lab asked for a while loop
            so I'll just manually count it out*/

            //if it gets to the nth term it breaks
            if (count == n)
              break;

            //switch to separate first 2 cases out
            switch (count)
            {
              case 0:
                break;
              case 1:
                total += 1;
                previous = 1;
                break;
              default:
                //adds the total of the set, then uses a temp int to move the sequence to the next set for the next round
                total += (previous + next);
                temp = next;
                next = (previous + next);
                previous = temp;
            }

            count++;//increment the count
        }

        return total;
    }

    /**
     * Main program to test both fibonacci methods
     */
    public static void main(String[] args)
    {
        System.out.println("Fibonacci number to compute:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        long startTime = System.nanoTime();
        System.out.println("Recursive fib: " + recFib(n));
        System.out.printf("Recursive Execution Time: %.2f ms\n\n", (System.nanoTime() - (double)startTime)/1000000);

        startTime = System.nanoTime();//reset
        System.out.println("Non-recursive fib: " + nonRecFib(n));
        System.out.printf("Non-Recursive Execution Time: %.2f ms\n\n", (System.nanoTime() - (double)startTime)/1000000);
    }

}
