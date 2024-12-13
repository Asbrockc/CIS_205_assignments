/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/2/20
 * Time: 11:50 AM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: HelloMain
 *
 * Description:
 *
 * ****************************************
 */
package lab05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Simple hello world program to introduce intelliJ IDE
 *
 * @author Christopher Asbrock
 * @version 1
 */
public class HelloMain
{

    public static final int NUM_INTS = 10;

    /**
     * main method
     *
     * @param args - for command line arguments (not used)
     */
    public static void main(String[] args)
    {
        //greets the user...
        greetUser();

        //gets an array of random numbers...
        int[] x = getArrayOfRandomNums();

        //output result
        System.out.println(Arrays.toString(x));
    }

    /**
     * creates an array NUM_INTS long filled with random intergers from 0 - 99
     *
     * @return - an int array full of random integers
     */
    private static int[] getArrayOfRandomNums()
    {
        //loop to test debugger
        Random rand = new Random();
        int[] x = new int[NUM_INTS];
        for (int i = 0; i < NUM_INTS; i++)
            x[i] = rand.nextInt(100);
        return x;
    }

    /**
     * A method to greet the user and give them a famous quote
     */
    private static void greetUser()
    {
        System.out.print("Hello. What is your name?" );
        System.out.println("A Personal Message for " + new Scanner(System.in).nextLine());
        System.out.println("When you kill time, remember that it has no resurrection");
        System.out.println("-- A.W. Tozer");
    }
}