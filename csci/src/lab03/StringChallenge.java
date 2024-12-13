/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2020
* Instructor: Christopher Dancy
* Section: 11:00am - 11:52am
*
* Name: Christopher Asbrock
* Date: 01/27/2020
*
* Lab / Assignment: Lab03
*
* Description: A program that uses various methods to mess with String objects
* *****************************************/

package lab03;

/**
 * The StringChallenge class is used to evaluate several different string
 * exercises from lab03.
 *
 * @author  Christopher Asbrock
 * @version 0.1
 */
public class StringChallenge
{

    /**A const string of vowels upper and lower to interate through to check*/
    public static final String nonVowels = "BCDFGHJKLMNPQRSTVWXYZ";


    /**
     * Count the number of non-vowels (consonants) out of all letters in a string.
     *
     * @param s The string to test
     * @return The number of vowels in <code>s</code>
     */
    public static int countNonVowels(String s)
    {
        int count = 0;

        //could use indexOf in my class nonvowel String and just look for -1 but I want to do a nested loop
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = 0; j < nonVowels.length(); j++)
            {
                if(s.toUpperCase().charAt(i) == nonVowels.charAt(j))
                    count++;
            }
        }
        return count;
    }


    /**
     * Count the number of non-vowels (consonants) out of all letters in a string.
     * However, this solution uses recursion.
     *
     * @param s The string to test
     * @return The number of vowels in <code>s</code>
     */
    public static int countNonVowelsRec(String s)
    {
      //easy enough, this time I'll use the index and look for -1 though.

        //base case if the length is 0 it's over
        if (s.length() == 0)
          return 0;
        //else look to see if the current value at index 0 is in my nonVowel String.
        //then a recursive call to itself while moving down the index
        else if (nonVowels.indexOf(s.toUpperCase().charAt(0)) != -1)
          return 1 + countNonVowelsRec(s.substring(1, s.length()));
        else
          return 0 + countNonVowelsRec(s.substring(1, s.length()));
    }


    /**
     * This method recursivly checks to see if the string is a pallendrom (the same if it is reversed)
     * it recursivly widdles down the string checking the beinging in end for maching characters, if
     * if the string is widdle down to the middle (0 or 1) it is a palendrome
     * if the beinging in end do not match then reversing it will be differnt, so it is not one
     *
     * @param s The string to test
     * @return True if the string is a pallendrom, false otherwise
     */
    public static boolean isPalindrome(String s)
    {
      //this widdles away at either side of the string recursivly
      if (s.length() == 0 || s.length() == 1)
        return true;
      if (s.charAt(0) == s.charAt(s.length() -1))
        return isPalindrome(s.substring(1, s.length() - 1));
      else
        return false;
    }


    /**
     * swaps the last and first letter of a string
     * prerequisits - the last and first letter must be a letter, otherwise it just returns the base
     * String back
     *
     * @param s The string to test
     * @return the String with first and last letter swapped, or the same string if the first and last are not letters
     */
    public static String swapLastAndFirstLetter(String s)
    {
        if (Character.isLetter(s.charAt(0)) && Character.isLetter(s.charAt(s.length()-1)))
        {
            //A couple empty chars to hold the new one based on Case of original
            char newBegin = ' ';
            char newEnd = ' ';
            //I am going right for the unicode here, if its greater then 90 'Z' its lowercase
            //**checks end to change begining, and beinging to change end
            if ((int)s.charAt(s.length()-1) > 90)
                newEnd = s.toLowerCase().charAt(0);
            else
                newEnd = s.toUpperCase().charAt(0);

            if ((int)s.charAt(0) > 90)
                newBegin = s.toLowerCase().charAt(s.length()-1);
            else
                newBegin = s.toUpperCase().charAt(s.length()-1);

            return (newBegin + s.substring(1, s.length() - 1) + newEnd);
        }
        else
            return s;
    }


    /**
     * takes in two string, any instances of the second string well be removed from the first
     * then the first string will be returned.
     * soooo... I am just replacing String.replace() with a perminent "" to remove it
     *
     * @param s The string to remove a string from
     * @param sRemove The string being removed
     * @return the s string with sRemove taken out of it
     */
    public static String withoutString(String s, String sRemove)
    {
        //returns s with sremoved replaced with ""
        return s.replace(sRemove, "");
    }


    /**
     * will take sequences of numbers in a string and add them together
     *
     * @param s The string to count numbers in
     * @return the total of the added up numbers
     */
    public static int sumNumbersInString(String s)
    {
        String temp = "";
        //probably a better way to do this but this is the first thing that jumped to mind.

        //rebuilds the string replacing everything that is not a number with a " "
        for (int i = 0; i < s.length(); i++)
        {
            if (!Character.isDigit(s.charAt(i)))
                temp += " ";
            else
                temp += s.charAt(i);
        }

        //splits the string into an array of string at any " "
        String [] leftOverNumbers = temp.split(" ");

        int total = 0;

        //loops through the new array and checks to see if the index is an empty string (cuase by the split) if not
        //then it is one of the leftover numbers and it will add it to the total
        for (int i = 0; i < leftOverNumbers.length; i++)
        {
            if (!leftOverNumbers[i].equals(""))
                total += Integer.parseInt(leftOverNumbers[i]);
        }

        //returns the total when calculated
        return total;
    }


    /**
     * Adds up the unicode value for all characters in a string
     *
     * @param s The string to add unicode values from
     * @return  the calculated value from adding unicode
     */
    public static int addUnicodeValues(String s)
    {
        int total = 0;

        for (int i = 0; i < s.length(); i++)
        {
            total += s.codePointAt(i);
        }

        return total;
    }
    

    /**
     * Main program to test out each String processing method
     */
    public static void main(String[] args)
    {

        String sTest = "Mississippi River";
        System.out.printf("countNonVowels(\"%s\") = %d\n", sTest, countNonVowels(sTest));
        System.out.printf("countNonVowelsRec(\"%s\") = %d\n", sTest, countNonVowelsRec(sTest));

        System.out.printf("isPalindrome(\"%s\") = %b\n", sTest, isPalindrome(sTest));
        sTest = "amanapanama";
        System.out.printf("isPalindrome(\"%s\") = %b\n", sTest, isPalindrome(sTest));

        sTest = "Testing";
        System.out.printf("swapLastAndFirstLetter(\"%s\") = \"%s\"\n", sTest, swapLastAndFirstLetter(sTest));
        sTest = "Testing123";
        System.out.printf("swapLastAndFirstLetter(\"%s\") = \"%s\"\n", sTest, swapLastAndFirstLetter(sTest));

        sTest = "Testing Running Walking Jumping";
        System.out.printf("withoutString(\"%s\",\"%s\") = \"%s\"\n", sTest, "ing", withoutString(sTest, "ing"));
        System.out.printf("withoutString(\"%s\",\"%s\") = \"%s\"\n", sTest, "Sleep", withoutString(sTest, "Sleep"));

        sTest = "a1b2c3";
        System.out.printf("sumNumbersInString(\"%s\") = %d\n", sTest, sumNumbersInString(sTest));
        sTest = "aa12bb34cc56dd78";
        System.out.printf("sumNumbersInString(\"%s\") = %d\n", sTest, sumNumbersInString(sTest));
        sTest = "a9D>Zc8";
        System.out.printf("addUnicodeValues(\"%s\") = %d\n", sTest, addUnicodeValues(sTest));
    }
}
