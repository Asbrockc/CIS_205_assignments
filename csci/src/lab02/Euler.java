/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2020
* Instructor: Christopher Dancy
* Section: 11:00am - 11:52am
*
* Name: Christopher Asbrock
* Date: 01/19/2020
*
* Lab / Assignment: Lab02
*
* Description:
* A simple exercise to to compute e, and to illustrate BigDecimal and
* its computational cost
* *****************************************
*/

package lab02;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Euler - a class of static methods to illustrate different ways to compute
 * Euler's number
 */
public class Euler
{

    /**
     * computeEulerTerm - compute the nth term of the power series of e^x
     *
     * @param n - The nth term of the power series to compute
     * @param x - the power of e used
     * @return - The computed term in the expansion
     */
    public static double computeEulerTerm(int n, double x)
    {
        double numerator = 1.0;
        double denominator = 1.0;

        for (int i = 1; i <= n; i++)
        {
          numerator *= x;
          denominator *= i;
        }

        return (numerator / denominator);
    }

    /**
     * compute Euler's number to the x power using the Taylor series expansion.
     * This is a bit inefficient. It calls a separate method to compute each
     * term in the expansion.
     *
     * @param x - compute e^x
     * @param numTerms - Number of terms to compute in the expansion
     * @return Euler's number
     */
    public static double computeEulerSlow(double x, int numTerms)
    {
        double eulerNum = 1;

        for (int i = 1; i <= numTerms; i++)
        {
            eulerNum += computeEulerTerm(i, x);
        }

        return eulerNum;
    }

    /**
     * compute Euler's number to the x power using the Taylor series expansion.
     * This is a faster implementation, using only one loop to compute the
     * result.
     *
     * @param x - compute e^x
     * @param numTerms - Number of terms to compute in the expansion
     * @return Euler's number
     */
    public static double computeEulerFast(double x, int numTerms)
    {
        //just need some extras to hold previous loops values to build on.
        double eulerNum = 1;
        double xPrevious = 1;
        double fPrevious = 1;

        //actually was simple... just builds on itself as it goes along and adds it to the total
        for (int i = 1; i <= numTerms; i++)
        {
            xPrevious = x * xPrevious;
            fPrevious = i * fPrevious;
            eulerNum += (xPrevious / fPrevious);
        }

        return eulerNum;
    }

    /**
     * computeEulerBig - compute e using BigDecimal so that we do not lose
     * accuracy
     *
     * @param x - the exponent of e to use
     * @param numTerms - the number of terms of the series to compute
     * @return - e as a BigDecimal
     */
    public static BigDecimal computeEulerBig(double x, int numTerms)
    {
      /*IMPORTANT Note: lab says to use the technique used in step 2, but since there is 4 steps listed
        I assumed step 1 was the slow method overall and step 2 was in one loop (which makes more sence anyway),
        so this method is in one loop using BigDecimal*/
        BigDecimal eulerNum = new BigDecimal(1);
        BigDecimal xPrevious = new BigDecimal(1);
        BigDecimal fPrevious = new BigDecimal(1);

        for (int i = 1; i <= numTerms; i++)
        {
            xPrevious = xPrevious.multiply(new BigDecimal(x));
            fPrevious = fPrevious.multiply(new BigDecimal(i));
            eulerNum = eulerNum.add(xPrevious.divide(fPrevious, MathContext.DECIMAL128)); //limit the decimal, but far enough down for it to be trival

        }

        return eulerNum;
    }

    /**
     * Main program to test out Euler computations
     */
    public static void main(String args[])
    {

        final int X = 5;
        final int NUM_TERMS = 20;

        System.out.printf("Evaluating e^%d using %d terms:\n", X, NUM_TERMS);

        long startTime = System.nanoTime();
        double dAnswer = Math.exp(X);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.printf("Math.exp answer:      %.20f : Elapsed time (ns): %d\n", dAnswer, elapsedTime);

        startTime = System.nanoTime();
        double num = computeEulerSlow(X, NUM_TERMS);
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("My slow answer:       %.20f : Elapsed time (ns): %d\n", num, elapsedTime);

        startTime = System.nanoTime();
        num = computeEulerFast(X, NUM_TERMS);
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("My fast answer:       %.20f : Elapsed time (ns): %d\n", num, elapsedTime);

        startTime = System.nanoTime();
        BigDecimal bigAnswer = computeEulerBig(X, NUM_TERMS);
        elapsedTime = System.nanoTime() - startTime;
        System.out.printf("My BigDecimal answer: %.20f : Elapsed time (ns): %d\n", bigAnswer, elapsedTime);
    }
}
