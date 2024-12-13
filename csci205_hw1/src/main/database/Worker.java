/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/3/20
 * Time: 8:06 PM
 *
 * Project: csci205_hw1
 * Package: main.database
 * Class: TempWorker
 *
 * Description:
 *
 * ****************************************
 */
package
        main.database;

//temp class so i can easily refactor when i need to (realised "String[]" would be horrible to try and change)
public class Worker
{
    String [] one;


    Worker(String...args)
    {
        one = new String[args.length];
        for (int i = 0; i < args.length; i++)
        {
            one[i] = args[i];
        }
    }

    public String[] getOne()
    {
        return one;
    }

}