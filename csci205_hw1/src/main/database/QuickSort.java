/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/4/20
 * Time: 9:22 PM
 *
 * Project: csci205_hw1
 * Package: main.database
 * Class: QuickSort
 *
 * Description:
 *
 * ****************************************
 */
package main.database;

import java.util.List;

/**
 * quick sort class because the bubble sort is archaic and slow
 * static with Generic methods and an operator for lambda implementation
 */
public class QuickSort extends SortUtil
{
    /**
     * quickSort simple sort call
     * not index options will just sort the entire array passed in
     *
     * @param list - array that is being sorted
     * @param operator - functional interface for lambda support
     * @param <T> - generic class so any List can be used
     */
    public static <T> void sort(List<T> list, QuickSort.Operation operator)
    {
        QuickSort.sort(list, 0 , list.size() - 1, operator);
    }

    /**
     * quickSort main driver splits into partitions and recursively calls
     * IF it is large enough to split
     *
     * @param list - array that is being sorted
     * @param first - first index
     * @param last - last index
     * @param operator - functional interface for lambda support
     * @param <T> - generic class so any List can be used
     */
    public static <T> void sort(List<T> list, int first, int last, QuickSort.Operation operator)
    {
        if (first < last)
        {
            int partitionPoint = sortPartition(list, first, last, operator);

            sort(list, first, partitionPoint - 1, operator);
            sort(list, partitionPoint + 1, last, operator);
        }
    }

    /**
     * takes a partition and swaps indexes to change all compared values to
     * the left or right of the pivot point as needed
     * ***helper method for QuickSort
     *
     * @param list - array to be sorted
     * @param first - first index
     * @param last - last index
     * @param operator - function interface for lambda expressions
     * @param <T> - generic type, takes any variation of the ArrayList
     * @return - the pivot index of the current pivot point
     */
    private static <T> int sortPartition(List<T> list, int first, int last, QuickSort.Operation operator)
    {
        T pivot = list.get(first);
        int pivotIndex = first;
        int value = 0;

        for (int i = first + 1; i <= last; i++)
        {
            value = operator.operation(list.get(i), pivot);
            if (value < 0)
            {
                swap(list, pivotIndex + 1, i);
                swap(list, pivotIndex, pivotIndex + 1);
                pivotIndex++;
            }
        }

        return pivotIndex;
    }
}