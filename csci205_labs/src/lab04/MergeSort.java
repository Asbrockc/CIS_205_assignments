/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2020
* Instructor: Christopher Dancy
* Section: 11:00am - 11:52am
*
* Name: Christopher Asbrock
* Date: 01/31/2020
*
* Lab / Assignment: Lab04
*
* Description: A program that sorts the contents of an int array using a merge
* sort
* *****************************************/
package lab04;

import java.util.Random;
import java.util.Arrays;

/**
 * Class that utilizes the merge sort to sort an array of integers
 * Merge sort algorithm referenced from https://en.wikipedia.org/wiki/Merge_sort and https://www.geeksforgeeks.org/python-program-for-merge-sort
 * wikipedia has a great little animation that helped visualize the merge sort and geeksforgeeks as a python implimentaiton that
 * helped type the recursive nature into it. Took advantage of Arrays methods to shorten the code since I needed the .toString from it anyway
 * I tried to make it less of a mess as well by creating the split arrays and an empty array then just updating the referenced array at the end.
 * (so there wasn't left, middles, and rights everywhere in the code)
 * And took separted the code into more then one method (becuase there is no point in everything being done in one giant method)
 *
 * @author Christopher Asbrock
 */
public class MergeSort
{
  /**Constant - number off integers per array*/
  public static final int NUM_INTS = 50;
  /**Constant - maximum value each integer can be (starting from 1 in this case)*/
  public static final int MAX_INT_VALUE = 100;
  /**Constant - the maximum numbers the program will print out (if it execeds this, it will not print anything)*/
  public static final int MAX_PRINTABLE = 50;

  /**
   * creates an int array full of random variables set with the Random class.
   *
   * @param amount - the size of the array to great / amount of integers created
   * @param maxValue - the maximum value a random integer can be
   * @return - an array of random integers based on the amount and maxvalues given
   */
  private static int [] createRandomArray(int amount, int maxValue)
  {
      int [] numberArray = new int[amount];
      Random rand = new Random();

      for (int i = 0; i < amount; i++)
          numberArray[i] = (rand.nextInt(maxValue) + 1);

      return numberArray;
  }

  /**
   * validates that an array has been sorted correctly
   *
   * @param array A reference to the array being validated
   * @return - true if the array is sorted, false otherwise
   */
  private static boolean validateArrayIsSorted(int [] array)
  {
      //compares each index with the one before it, if the one before it is greater, it is not sorted: return false
      for (int i = 1; i < array.length; i++)
          if (array[i - 1] > array[i])
              return false;

      //if it makes its through the loop its good to go
      return true;
  }


  /**
   * primary array merge, takes in the left and right sections created, campares and combines them
   * returns a new sorted section.
   *
   * @param arrayLeft an array of integers made up of the left side of the split
   * @param arrayRight an array of integers made up of the right side of the split
   * @return - the combined sorted arrays
   */
  private static int[] sortArrays (int [] arrayLeft, int [] arrayRight)
  {
    int [] tempArray = new int[arrayLeft.length + arrayRight.length];

    //3 initilized start points to interate through the left array(i), right array(j), and temparray array(k)
    int i = 0;
    int j = 0;
    int k = 0;

    //itrates through the split arrays, compares the values, and combines them in order in the tamparay array
    while (i < arrayLeft.length && j < arrayRight.length)
    {
        if (arrayLeft[i] <= arrayRight[j])
        {
            tempArray[k] = arrayLeft[i];
            i += 1;
        }
        else
        {
            tempArray[k] = arrayRight[j];
            j += 1;
        }

        k += 1;
    }

    //increments through the rest of left array and adds it to the temparary array
    for (i = i; i < arrayLeft.length; i++)
    {
        tempArray[k] = arrayLeft[i];
        k += 1;
    }

    //increment through the rest of right array and adds it to the temparary array
    for (j = j; j < arrayRight.length; j++)
    {
        tempArray[k] = arrayRight[j];
        k += 1;
    }

    //returns new sorted array
    return tempArray;
  }

  /**
   * A private methdo used by mergeSort to copair and combine 2 sides of the arrays
   * takes in a begining, middle, and end point,
   *
   * @param array A reference to the array being sorted
   * @param left the left index of the first section
   * @param middle the split point where the array is separted (left -> middle / middle + 1 -> right)
   * @param right the right index where the second array ends
   */
  private static void mergeArrays(int [] array, int left, int middle, int right)
  {
      //creates a sorted section of reference array, using the partitians of the reference array marked by left,middle, and right
      int [] sortedSection = sortArrays(
          Arrays.copyOfRange(array, left, middle + 1),
          Arrays.copyOfRange(array, middle + 1, right + 1));

      //updates the referenced array using the sorted section
      for (int i = left; i <= right; i ++)
          array[i] = sortedSection[i - left];
  }

  /**
   * The main mergeSort driving method
   * Defines the midpoint and recursivly calls itself till the break case
   * The break case is when the left and right values are the same (i. e. the array is too small to split)
   *
   * @param array A reference to the array being sorted
   * @param left the left index of the current interation
   * @param right the right index of the current interation
   */
  public static void mergeSort(int [] array, int left, int right)
  {
      if (left < right)
      {
          //find the mid point to split in two equal sizes
          int middle = ((right + left) / 2);

          //call merSort on both left and right arrays
          mergeSort(array, left, middle);
          mergeSort(array, middle + 1, right);

          //merge the sorted sublists
          mergeArrays(array, left, middle, right);
      }
  }

  /**
   * Main test program,
   * creates a randomized array of integers based on the classes predeffined maximum number of integers, and maximum value of integers.
   * if the number of integers un the array is less then MAX_PRINTABLE, the unsorted and sorted lists will be printed;
   * The program will check if the array was properly sorted and either note its success or failure
   *
   * @param args - standard command line argument - NOT USED*
   */
  public static void main(String [] args)
  {
      //array of random integers
      int [] x = createRandomArray(NUM_INTS, MAX_INT_VALUE);

      if (NUM_INTS <= MAX_PRINTABLE)
          System.out.println("Array before sorting:\n" + Arrays.toString(x));

      //calls the mergeSort to sort the array
      mergeSort(x, 0 , x.length - 1);

      if (NUM_INTS <= MAX_PRINTABLE)
          System.out.println("Array after sorting:\n" + Arrays.toString(x));

      //validates the sorted array
      System.out.println(validateArrayIsSorted(x) ? "SUCCESS!" : "FAILED!");
  }
}
