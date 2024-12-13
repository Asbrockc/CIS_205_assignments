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
* Description: A simple calculator program hasilty written to toy with switches
* *****************************************/
package lab04;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *  Calculator class, contrains metiods for taking in user input, checking its vaiability
 *  then calculating the results of several differnt math operations
 *
 * @author Christopher Asbrock
 */
public class Calculator
{
  /**Constant - addition operator*/
  public static final String ADD = "+";
  /**Constant - subtraction operator*/
  public static final String SUBTRACT = "-";
  /**Constant - product operator*/
  public static final String MULTIPLY = "*";
  /**Constant - divition operator*/
  public static final String DIVIDE = "/";
  /**Constant - modulus operator*/
  public static final String MOD = "%";
  /**Constant - power operator*/
  public static final String POWER = "^";


  /**
   * Takes in two paramters and adds them together
   *
   * @param valueOne - the first value of the equation
   * @param valueTwo - the second value of the equation
   * @return - the calculated sum of valueOne and valueTwo
   */
  private static float add(float valueOne, float valueTwo)
  {
      return (valueOne + valueTwo);
  }

  /**
   * Takes in two paramters and subtracts the first from the second
   *
   * @param valueOne - the first value of the equation
   * @param valueTwo - the second value of the equation
   * @return - the calculated subtration of valueOne from valueTwo
   */
  private static float subtract(float valueOne, float valueTwo)
  {
      return (valueOne - valueTwo);
  }

  /**
   * Takes in two paramters and multiplies them together
   *
   * @param valueOne - the first value of the equatio
   * @param valueTwo - the second value of the equation
   * @return - the calculated sum of valueOne and valueTwo
   */
  private static float multiply(float valueOne, float valueTwo)
  {
      return (valueOne * valueTwo);
  }

  /**
   * Takes in two paramters and divides the first from the second
   *
   * @param valueOne - the first value of the equatio
   * @param valueTwo - the second value of the equation
   * @return - the calculated sum of valueOne and valueTwo
   * @throws - DivideByZeroExeption if the second value is 0
   */
  private static float divide(float valueOne, float valueTwo) throws DivideByZeroException
  {
      //if the value of the second operand is 0 it will throw and exception to inform the user
      if (valueTwo == 0)
          throw new DivideByZeroException();
      return (valueOne / valueTwo);
  }

  /**
   * Takes in two paramters and modulates them together
   *
   * @param valueOne - the first value of the equatio
   * @param valueTwo - the second value of the equation
   * @return - the calculated modulus of valueOne and valueTwo
   * @throws - DivideByZeroExeption if the second value is 0
   */
  private static float mod(float valueOne, float valueTwo) throws DivideByZeroException
  {
      //if the value of the second operand is 0 it will throw and exception to inform the user
      if (valueTwo == 0)
          throw new DivideByZeroException();
      return (valueOne % valueTwo);
  }

  /**
   * Takes in two paramters and raises the first to the power of the second
   *
   * @param valueOne - the first value of the equatio
   * @param valueTwo - the power to raise value One to
   * @return - the calculated product of valueOne raised to valueTwo
   */
  private static float power(float valueOne, float valueTwo)
  {
      float start = valueOne;

      //just loops through and times the first value by itself as many times as it needs to
      for (int i = 1; i < valueTwo; i++)
          start *= valueOne;

      return start;
  }

  /**
   * uses a switch to decide what operation to use with value one and two
   * then calculates and returns the answer
   *
   * @param valueOne - the first operand
   * @param operator - A string representation of the operator to use
   * @param valueTwo - the second operand
   * @return - the calculated value
   * @throws - DivideByZeroExeption if an equation is trying to divide by 0 (or modulus by 0)
   * @throws - NumberFormatException if any improper formats are used by the user
   */
  public static float calculateOperation(float valueOne, String operator, float valueTwo) throws DivideByZeroException, NumberFormatException
  {
      float answer = 0;

      switch (operator)
      {
        case ADD:
            answer = add(valueOne, valueTwo);
            break;
        case SUBTRACT:
            answer = subtract(valueOne, valueTwo);
            break;
        case MULTIPLY:
            answer = multiply(valueOne, valueTwo);
            break;
        case DIVIDE:
            answer = divide(valueOne, valueTwo);
            break;
        case MOD:
            answer = mod(valueOne, valueTwo);
            break;
        case POWER:
            answer = power(valueOne, valueTwo);
            break;
        default:
            //will get here if there is an improper operator os mistake in the string
            throw new NumberFormatException("ERROR: Operator Missing");
      }

      return answer;
  }

  /**
   * Takes in two paramters and modulates them together
   *
   * @param equation - A string representation of an equation: (i.e. "1 + 1")
   * @return - the calculated value
   * @throws - DivideByZeroExeption if an equation is trying to divide by 0 (or modulus by 0)
   * @throws - NumberFormatException if any improper formats are used by the user
   */
  public static float operator(String equation) throws DivideByZeroException, NumberFormatException
  {
      //removes pre and post spaces with trim() then splits the string into an array
      String [] equationSubset = equation.trim().split(" ");

      //if there is more or less then 3 then the format was wrong, throws an excpetion
      if (equationSubset.length != 3)
          throw new NumberFormatException("ERROR: Improper Format");

      //two floats to hold the parsed values
      float valueOne = 0;
      float valueTwo = 0;

      //purely a helper int to determine which operator failed in case of formate error
      int operand = 1;

      //makes sure the flaots succesfully parse; throws an exception if not
      try
      {
          valueOne = Float.parseFloat(equationSubset[0]);
          operand++;
          valueTwo = Float.parseFloat(equationSubset[2]);
      }
      catch (NumberFormatException e)
      {
          throw new NumberFormatException("ERROR: Cannot Parse Operand " + String.valueOf(operand));
      }

      //finally if everything checks out, it will call the calculate operation to find the answer
      float answer = calculateOperation(valueOne,equationSubset[1],valueTwo);

      return answer;
  }

  /**
   * takes in the next line from the user
   *
   * @return - the String representation of next line
   */
  public static String getInput()
  {
      Scanner in = new Scanner(System.in);

      return in.nextLine();
  }

  /**
   * The main driver program for the calculateor class
   * prompts the user for a String representation of an equation
   * ditermines if the string in a usable format
   * caluclates and prints the result
   * program loops till the keyword "end" is used by the user
   */
  public static void calculatorDriver()
  {
    while (true)
    {
        try
        {
            System.out.print("Enter a simple arithmetic expression with spacing (type END to quit): ");
            String input = getInput();

            //instead of looping to an option, this will just loop till the user enteres "end"
            //handeling and checking string so i might as well take advantage
            if (input.replace(" ", "").equalsIgnoreCase("end"))
                break;

            //will check input and calulate answer
            float answer = operator(input);

            //formats the answer in typical number formats (t 4 decimal places)
            DecimalFormat formater = new DecimalFormat("###,###,###,###.####");
            System.out.println(input.trim() + " = " + formater.format(answer));
        }
        catch (DivideByZeroException e)
        {
            //divition by 0
            System.out.println("ERROR: Cannot Divide by 0");
        }
        catch (NumberFormatException e)
        {
            //formating problems; passes a message depending on the problem
            System.out.println(e.getMessage());
        }
    }

  }

  /**
   * The main method
   * initilizes the calculatorDriver for testing
   */
  public static void main(String [] args)
  {
      //just makes a call to the driver
      calculatorDriver();
  }

  /**
   * A custom exception class for throwing and catching divide by 0 errors
   */
  public static class DivideByZeroException extends Exception{}
}
