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
* Description: Simple program to convert an integer input by the user into Fahrenheit.
* I also added some exemption in to try to jar some memories.
* *****************************************/

package lab02;

import java.util.Scanner;

public class CtoF
{
    //temperature climate caps
    public static final int freeze_cap = 32;
    public static final int cool_cap = 60;
    public static final int warm_cap = 85;

    public static void main(String[] args)
    {
        while (true)
        {
          // Prompt user for a Celsius temp
          System.out.print("Enter a temperature in Celsius: ");

          // Create the Scanner object, attached to console input
          Scanner in = new Scanner(System.in);

          // Read and store the Celsius temp
          int temperatureC;

          //loops through and prompt for an integer value: added exempltion becuase...why not
          while (true)
          {
            try
            {
              temperatureC = Integer.parseInt(in.nextLine());
              break;
            }
            catch(NumberFormatException e)
            {
              System.out.print("Value must be an integer: ");
            }
          }

          // Convert the temp to Fahrenheit
          float temperatureF = (float)temperatureC * 9 / 5 + 32;

          // Output the results with correct formatting, one significant digit
          System.out.printf("%d C = %.1f F\n", temperatureC , temperatureF);

          //if/else : checks tempurature and comments based on its range
          if (temperatureF <= freeze_cap)
            System.out.println("Brrr... it is FREEZING out!");
          else if (temperatureF <= cool_cap)
            System.out.println("It's a bit cool out");
          else if (temperatureF <= warm_cap)
            System.out.println("It's comfortabley warm");
          else
            System.out.println("It's HOT! I need A/C!");

          //prompts user to continue, if not breaks main loops
          System.out.print("Try again? [y | n]: ");
          if (!in.next().equalsIgnoreCase("y"))
            break;
      }

      System.out.println("Goodbye!");

    }
}
