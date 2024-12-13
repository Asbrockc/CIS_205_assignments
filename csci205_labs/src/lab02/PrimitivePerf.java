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
* This is a simple program to help you understand wrapper classes and autoboxing,
* and understand that object convenience may come at a computational cost.
* *****************************************/

package lab02;

public class PrimitivePerf
{

    /** The quantity of numbers to compute a sum for */
    static final int MAX_NUMBERS = 10000000;

    /** MAIN PROGRAM */
    public static void main(String[] args)
    {
        // Evaluate the test with primitive types
        long ts = System.nanoTime();
        long primitiveResult = testPrimitive();
        long primitiveDuration = System.nanoTime() - ts;

        // Now, evaluate the test with the wrapper class type
        ts = System.nanoTime();
        Long wrappedResult = testWrapped();
        long wrappedDuration = System.nanoTime() - ts;

        // TODO - Print results, timing, and pct difference between times
        System.out.printf("Primitive:\nAnswer = %d. Time = %d ns\n\nWrapped:\nAnswer = %d. Time = %d ns\nPrimitive types used %.1f%% of the time of wrapper objects.\n",
          primitiveResult,
          primitiveDuration,
          wrappedResult,
          wrappedDuration,
          (((double)primitiveDuration / wrappedDuration) * 100));

    }

    /**
     * Simple function to evaluate the speed of adding numbers that are
     * primitive types
     */
    public static long testPrimitive()
    {
        // Store the result as a primitive type
        long sum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++)
        {
            sum = sum + i;
        }

        return sum;
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are stored
     * as an object.
     */
    public static Long testWrapped()
    {
        // Store the result as an object Long, not the primitive type!
        Long objSum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++)
        {
            objSum = objSum + i;
        }

        return objSum;
    }
}
