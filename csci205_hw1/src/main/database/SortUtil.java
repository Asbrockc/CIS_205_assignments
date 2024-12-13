/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/4/20
 * Time: 9:59 PM
 *
 * Project: csci205_hw1
 * Package: main.database
 * Class: Sort
 *
 * Description:
 *
 * ****************************************
 */
package
        main.database;

import java.util.List;

/**
 * sort abstract class that defines commons swap and functional interface used by both sorts... or all sorts if i get
 * bored and add more
 *
 * @author Christopher Asbrock
 */
public abstract class SortUtil
{
    /**
     * swap method to swap to parts of an array list
     *
     * @param list - the list that needs two elements swapped
     * @param i - first index
     * @param j - index to swap with
     * @param <T> - Generic method.. I think E is standard in java now that I think of it
     */
    static <T> void swap(List<T> list, int i, int j)
    {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * functional interface to use lambda to control bubble sort
     */
    public interface Operation
    {
        public int operation(Object a, Object b);
    }
}