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
* Description: A simple program that finds the volume of a sphere.
* *****************************************/

package lab02;

import java.util.Scanner;
import java.lang.Math;

public class Sphere
{
    //calculates the valume of a sphere (4/3 * PI * rad^3), then returns the result;
    public static double calculateVolume(int radius)
    {
        return (Math.PI * Math.pow(radius, 3) * 4/3);
    }

    //prompts user for the radius of a circle, then returns the value
    public static int getRadius()
    {
        Scanner in = new Scanner(System.in);
        int radius;

        //loops untill raidus is an integer value: again like Ctof wasn't asked to exception but avioding such a blatant crash has to be done
        while (true)
        {
          System.out.print("Enter the Radius of a sphere: ");

          try
          {
            radius = Integer.parseInt(in.nextLine());
            break;
          }
          catch(NumberFormatException e)
          {
            //will just re-prompt radius amount
          }
        }

        //return users input value
        return radius;
    }

    public static void main(String[] args)
    {
        //collects user input, then calculates the spheres volume based off of radius.
        double volume = calculateVolume(getRadius());

        //print result in required format
        System.out.printf("The volume is: %.2f\nRounded to nearest integer: %d\n", volume, Math.round(volume));
    }
}
