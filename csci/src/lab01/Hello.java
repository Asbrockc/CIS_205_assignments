/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2020
* Instructor: Christopher Dancy
* Section: 11:00am - 11:52am
*
* Name: Christopher Asbrock
* Date: 01/15/2020
*
* Lab / Assignment: Lab01
*
* Description: A hello world program, becuase its computer law that those who do not
*   write hello world programs to learn new things will have there hands broken so they can
*   never code again.
* *****************************************/

package lab01;

public class Hello
{
  public static void main(String[] args)
  {
    long startTime = System.nanoTime();

    System.out.println("Programming is not a spectator sport");

    //pretty sure this is what I need for miliseconds, yup reading the rest of the lab clarified it
    System.out.printf("Time to execute: %.4f ms\n", (System.nanoTime() - (double)startTime) / 1000000);
    System.exit(0);
  }
}
