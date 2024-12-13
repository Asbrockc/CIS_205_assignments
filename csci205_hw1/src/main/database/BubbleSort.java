/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/2/20
 * Time: 10:52 PM
 *
 * Project: csci205_hw1
 * Package: main
 * Class: BubbleSort
 *
 * Description:
 *
 * ****************************************
 */
package main.database;

import java.util.List;

/**
 * static BubbleSort class that uses the bubble sort algorithm to sort lists
 *
 * @author Christopher Asbrock
 */
public class BubbleSort extends SortUtil
{
    /**
     * sort an ArrayList based on the lambda operation passed in
     *
     * @param list - the list to sort
     * @param operator - the functional interface to be used by lambda
     * @param <T> - template method... or whatever javas name for template was
     */
    static <T> void sort(List<T> list, SortUtil.Operation operator)
    {
        int value = 0;

        for (int i = 0; i < list.size(); i++)
        {
            for (int j = 0; j < list.size() - 1; j++)
            {
                value = operator.operation(list.get(j) ,list.get(j + 1));
                if (value > 0)
                {
                    SortUtil.swap(list, j, j + 1);
                }
            }
        }
    }
}