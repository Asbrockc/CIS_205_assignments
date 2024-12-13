/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/2/20
 * Time: 12:52 PM
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: MagicSquareChecker
 *
 * Description: A class that will check if a 2x2 array of integers representing a game of magic square is correct or not
 *
 * ****************************************
 */
package lab05;

/**
 * A class that checks 2x2 arrays of integers representing games of the magic square puzzle for correctness
 * puzzles are correct if they have 1 through total cell number with no duplicates, and each row, column, and diagnal; add up
 * to the same number
 *
 * @author Christopher Asbrock
 * @version 1...
 */
public class MagicSquareChecker
{

    /**A good copy of a 3x3 magic square for testing*/
    static final int[][] MAGIC_SQUARE_3_GOOD =
            {
                    {8, 1, 6},
                    {3, 5, 7},
                    {4, 9, 2}
            };

    /**A good copy of a 4x4 magic square for testing*/
    static final int[][] MAGIC_SQUARE_4_GOOD =
            {
                    {16, 3, 2, 13},
                    {5, 10, 11, 8},
                    {9, 6, 7, 12},
                    {4, 15, 14, 1}
            };

    /**A very large good copy of a 10x10 magic square for testing*/
    static final int[][] MAGIC_SQUARE_10_GOOD =
            {
                    {68, 65, 96, 93, 4, 1, 32, 29, 60, 57},
                    {66, 67, 94, 95, 2, 3, 30, 31, 58, 59},
                    {92, 89, 20, 17, 28, 25, 56, 53, 64, 61},
                    {90, 91, 18, 19, 26, 27, 54, 55, 62, 63},
                    {16,13,24,21,49,52,80,77,88,85},
                    {14,15,22,23,50,51,78,79,86,87},
                    {37,40,45,48,76,73,81,84,9,12},
                    {38,39,46,47,74,75,82,83,10,11},
                    {41,44,69,72,97,100,5,8,33,36},
                    {43,42,71,70,99,98,7,6,35,34}
            };

    /**A bad copy of a 3x3 magic square for testing*/
    static final int[][] MAGIC_SQUARE_3_BAD =
            {
                    {8, 1, 4},
                    {3, 5, 7},
                    {6, 9, 2}
            };

    /**A bad copy of 4x4 magic square for testing*/
    static final int[][] MAGIC_SQUARE_4_BAD =
            {
                    {16, 2, 2, 13},
                    {5, 10, 11, 8},
                    {9, 6, 7, 12},
                    {4, 15, 14, 1}
            };

    /**A very large BAD copy of a 10x10 magic square for testing*/
    static final int[][] MAGIC_SQUARE_10_BAD =
            {
                    {68, 65, 96, 93, 4, 1, 32, 29, 60, 57},
                    {66, 67, 94, 95, 2, 3, 30, 31, 58, 59},
                    {92, 89, 20, 17, 28, 25, 56, 53, 64, 61},
                    {90, 91, 18, 19, 26, 27, 54, 55, 62, 63},
                    {16,13,24,21,49,52,80,77,88,85},
                    {14,15,23,22,50,51,78,79,86,87},
                    {37,40,45,48,76,73,81,84,9,12},
                    {38,39,46,47,74,75,82,83,10,11},
                    {41,44,69,72,97,100,5,8,33,36},
                    {43,42,71,70,99,98,7,6,35,34}
            };


    /**
     * will take an integer array preprsenting a row, col, or diagnal of a magic square; and the total they should add up to
     * if the total of the array does not add up to the total given return false
     *
     * @param total - the total the row,col,or diag should add up to
     * @param rowColNumbers - the array of integers
     * @return true if the numbers add up to the correct total; false otherwise
     */
    public static boolean totalRowColCheck(int total, int[] rowColNumbers)
    {
        //gets the total added up value of the array passed in
        int check = totalNumbers(rowColNumbers);

        //then makes sure its what it should be; if not, return false
        if (total != check)
            return false;

        return true;
    }


    /**
     * Takes an array of integers and adds the contents
     *
     * @param numbers - the array of integers to add up
     * @return - the calculated total
     */
    private static int totalNumbers(int[] numbers)
    {
        int check = 0;
        for (int i = 0; i < numbers.length ; i++)
            check += numbers[i];

        //adds up the arrays into a total and returns the total
        return check;
    }


    /**
     * driver program for the magic square checker, will make a call and check if the magic square has the correct numbers
     * in it and then if the rows,columns, and diagnals add up to the correct amount
     *
     * @param magicSquare - the 2x2 integer array representing a finished magic square
     * @return true if both checks are passed; false otherwise
     */
    public static boolean checkMagicSquare(int [][] magicSquare)
    {
        //checks to make sure row, col, daigs, add up to the same amount
        if (!checkMagicSquareTotals(magicSquare))
            return false;

        //check to make sure 1 of each value is in the puzzle
        if (!checkMagicSquareValues(magicSquare))
            return false;

        //if the other two check out its good to go
        return true;
    }


    /**
     * will take in a 2x2 integer array representing a magic square
     * will check its rows, columns, and diagnals to make sure that they are all equal to eachother
     * if not, the puzzle is incorrect
     *
     * @param magicSquare - the 2x2 integer array representation of a magic square puzzle
     * @return - true if rows, cols, and diag boxes equal one another; false otherwise
     */
    private static boolean checkMagicSquareValues(int[][] magicSquare)
    {
        //use the first row to check for a total to start with
        int baseTotal = totalNumbers(magicSquare[0]);

        //check each total of every row of the magic square to make sure they are all the same
        if (checkMagicSquareValuesRows(magicSquare, baseTotal))
            return false;
        if (checkMagicSquareValuesCols(magicSquare, baseTotal))
            return false;
        if (checkMagicSquareValuesDiags(magicSquare, baseTotal))
            return false;

        //if it makes it through each test then the puzzle is correct return true
        return true;
    }


    /**
     * takes a 2x2 integer array representing a magic square and checks its columns for correctness
     *
     * @param magicSquare - the 2x2 integer array representing a magic square to check
     * @param baseTotal - the total the column should add up to
     * @return - true if an error was found; false otherwise
     */
    private static boolean checkMagicSquareValuesCols(int[][] magicSquare, int baseTotal)
    {
        //loop through the entire array to build up temp arrays for testing
        for (int i = 0; i < magicSquare.length; i++)
        {
            //temp array for bulding up columns
            int [] tempArray = new int[magicSquare.length];

            for (int j = 0; j < magicSquare[i].length; j++)
            {
                tempArray[j] = magicSquare[j][i];
            }

            //when a column is filled test for total
            if (!totalRowColCheck(baseTotal, tempArray))
            {
                System.out.println("Coloumn " + (i + 1) + " Adds Up To Different Value!");
                return true;
            }
        }

        return false;
    }


    /**
     * takes a 2x2 integer array representing a magic square and checks its diagonals for correctness
     *
     * @param magicSquare - the 2x2 integer array representing a magic square to check
     * @param baseTotal - the total the diagonal should add up to
     * @return - true if an error was found; false otherwise
     */
    private static boolean checkMagicSquareValuesDiags(int[][] magicSquare, int baseTotal)
    {
        //an array to contain diagnal values (top-left -> bottom-right)
        int[] downSlopeDiag = new int[magicSquare.length];
        //an array to contain diagnal values (bottom-left -> top-right)
        int[] upSlopeDiag = new int[magicSquare.length];

        //loop through the entire array to build up temp arrays for testing
        for (int i = 0; i < magicSquare.length; i++)
        {
            downSlopeDiag[i] = magicSquare[i][i];
            upSlopeDiag[i] = magicSquare[magicSquare.length - 1 - i][i];
        }

        //if it gets to this point test diagnal totals for correctness
        if (!totalRowColCheck(baseTotal, downSlopeDiag))
        {
            System.out.println("(top-left -> bottom-right) Diagnal Row Adds Up To Different Value!");
            return true;
        }

        if (!totalRowColCheck(baseTotal, upSlopeDiag))
        {
            System.out.println("(botoom-left -> top-right) Diagnal Row Adds Up To Different Value!");
            return true;
        }

        return false;
    }


    /**
     * takes a 2x2 integer array representing a magic square and checks its rows for correctness
     *
     * @param magicSquare - the 2x2 integer array representing a magic square to check
     * @param baseTotal - the total the row should add up to
     * @return - true if an error was found; false otherwise
     */
    private static boolean checkMagicSquareValuesRows(int[][] magicSquare, int baseTotal)
    {
        for (int i = 0; i < magicSquare.length; i++)
        {
            //loops through and checks each row for the total value; if it is not the same then yes this is invalid return true;
            if (!totalRowColCheck(baseTotal, magicSquare[i]))
            {
                System.out.println("Row " + (i + 1) + " Adds Up To Different Value!");
                return true;
            }
        }

        //if it makes it through the loop no errors where found
        return false;
    }


    /**
     * Takes in a 2x2 int array representation of a magic square game, runs through and makes sure
     * that all integers are used and none are dublicated
     * if it is missing one or finds a dublicate the puzzle is not correct
     *
     * @param magicSquare - the 2x2 int array representation of the magic square to test
     * @return - true if the puzzle follows the base rules; false if it is incorrect
     */
    private static boolean checkMagicSquareTotals(int [][] magicSquare)
    {
        //counts off occurances of a number
        int count = 0;


        //outter loop, the number to look for
        for (int i = 1; i <= magicSquare.length * magicSquare[0].length; i++)
        {
            //nested loops interate through the 2x2 array
            for (int j = 0; j < magicSquare.length; j++)
            {
                for (int k = 0; k < magicSquare[j].length; k++)
                {
                    //if it finds an occurance incrment the counter
                    if (i == magicSquare[j][k])
                        count++;
                }

            }

            //if the count is anything other then 1, there either more then one or none; either way its incorect; return false
            if (count != 1)
            {
                System.out.println("Number " + (i) + " Has Too Many Values!");
                return false;
            }

            //reset count for next number round
            count = 0;
        }

        return true;
    }


    /**
     * Takes in a 2x2 magic square and will format it to the screen with lines representing each box
     *
     * @param magicSquare - the magic square to print
     */
    public static void printSquare(int [][] magicSquare)
    {
        //iterate through the 2x2 array
        for (int i = 0; i < magicSquare.length; i++)
        {
            //print the top lines of the box bases on its length
            System.out.print(printBoxLine(magicSquare.length));

            for (int j = 0; j < magicSquare[i].length; j++)
            {
                //inline check, if its the first number print the pre '|' to close the left side of the boxes
                System.out.printf
                        (
                                (j == 0 ? "|" : "") +
                                " %2s | ", magicSquare[i][j]
                        );
            }

            //at the end of each row check and see if its at the bottom, if not go to next line; if yes print the bottom line
           System.out.print((i == magicSquare.length - 1) ? "\n" + printBoxLine(magicSquare.length) : "\n");
        }
    }


    /**
     * prints a line of '_' characters for astetic purposes
     * used by magic square print for formating
     *
     * @param length - length of box so the lines will match the array
     * @return - the formated line segment for printing
     */
    private static String printBoxLine(int length)
    {
        String line = "";

        for (int k = 0; k < length; k++)
        {
            line += (" ____ " + (k == length - 1 ? "\n" : ""));
        }

        return line;
    }


    /**
     * Takes in various 2x2 arrays representing magic squares with answers, and tests to see
     * if they were done successfully or not
     *
     * @param magicSquares - An array of 2x2 int array representation of magic squares
     */
    private static void magicSquareTestDriver(int[][]...magicSquares)
    {
        for (int i = 0; i < magicSquares.length ; i++)
        {
            System.out.println("Checking Square:");
            printSquare(magicSquares[i]);
            System.out.println((checkMagicSquare(magicSquares[i])) ? "SUCCESS\n" : "FAIL\n");
        }
    }


    /**
     * main method - calls the magic square driver to check hardcoded squares for correctness
     *
     * @param args - command line arguments - NOT USED
     */
    public static void main(String[] args)
    {
        magicSquareTestDriver(
                MAGIC_SQUARE_3_GOOD,
                MAGIC_SQUARE_4_GOOD,
                MAGIC_SQUARE_10_GOOD,
                MAGIC_SQUARE_3_BAD,
                MAGIC_SQUARE_4_BAD,
                MAGIC_SQUARE_10_BAD
                );
    }

}