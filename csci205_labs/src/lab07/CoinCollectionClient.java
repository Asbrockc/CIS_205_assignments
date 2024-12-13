/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/10/20
 * Time: 7:29 PM
 *
 * Project: csci205_labs
 * Package: lab07
 * Class: CoinCollectionClient
 *
 * Description: GUI coin tally
 *
 * ****************************************
 */
package lab07;

import javax.swing.*;
import java.awt.*;

/**
 * creates a user interface for a CoinCollection
 *
 * @author Christopher Asbrock
 */
public class CoinCollectionClient
{
    /**
     * default constructor
     */
    CoinCollectionClient()
    {
        super();
    }

    /**
     * enum of choices and what the  represent for the menu
     */
    private enum AddRemoveChoices
    {
        ADD("Add Coins"), REMOVE("Remove Coins"), DONE("Done");

        private String label;

        AddRemoveChoices(String s)
        {
            this.label = s;
        }

        @Override
        public String toString()
        {
            return label;
        }
    }

    /**
     * main program for testing CoinCollectionClient class
     * @param args - command line arguments (NOT - USED)
     */
    public static void main(String[] args)
    {
        CoinCollection myCoins = new CoinCollection();

        myCoins.addCoins(Coin.NICKEL, 5);
        myCoins.addCoins(Coin.DIME, 3);
        myCoins.addCoins(Coin.QUARTER, 7);

        CoinCollectionClient client = new CoinCollectionClient();

        client.coinCollectionDriver(myCoins);
        client.finalDisplay(myCoins);


    }

    /**
     * driver program for the coin collection prompts
     * displays options, uses a switch to manage choices, and will always end with the final display showing the coin amounts
     *
     * @param myCoins - a reference to the current set of coins being tallied
     */
    private void coinCollectionDriver(CoinCollection myCoins)
    {
        //loop driver, forgot the trick to breaking a loop and switch from the witch break, but this works just as well
        boolean running = true;

        while (running)
        {
            try
            {
                int choice = getChoice(myCoins);
                Coin coinChoice;
                int amount = 0;

                switch (choice)
                {
                    case 0:
                        coinChoice = selectCoin();
                        amount = getAmount("How many do you want to add?",
                                "Add coins",
                                myCoins,
                                coinChoice);
                        myCoins.addCoins(coinChoice, amount);
                        break;
                    case 1:
                        coinChoice = selectCoin();
                        amount = getAmount("How many do you want to remove? Max = " + String.valueOf(myCoins.getCount(coinChoice)),
                                "Remove coins",
                                myCoins,
                                coinChoice);
                        myCoins.removeCoins(coinChoice, amount);
                        break;
                    case 2:
                        running = false;
                        break;

                    //this should catch the x return and end just the same as the done button
                    default:
                        running = false;
                }
            }
            catch (NumberFormatException e)
            {
                /*
                    know exceptions already so I'll take advantage of them
                    if any button other then a relevant one is hit it will throw a NumberFormatException and gracefully
                    end the program with the final dialog
                */
                running = false;
            }
        }
    }

    /**
     * Takes the current coins values and displays the final results to the user
     * *** will always display at the closing of the program through any means
     *
     * @param myCoins - the reference to the current mycoins collection
     */
    private void finalDisplay(CoinCollection myCoins)
    {
        //used to javaFX my swings way out of date, but I just refocused the other menu around only one option here
        JOptionPane.showOptionDialog(null,
                "You have:\n" + myCoins + "\n\nGoodbye!",
                "Goodbye!",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                null, new String[]{"OK"},
                "ok");
    }

    /**
     * Displays a menu to get the amount of coins to change
     *
     * @param message - the message to promt the user (add or remove)
     * @param title - the title of the pane (add or remove)
     * @param myCoins - the reference to the current coin collection being worked on
     * @param coinChoice - the coin currenty being changed
     * @return - the amount to add or remove
     * @throws NumberFormatException - thrown if input is not an integer, or if cancel or x was hit; to end the program
     */
    private int getAmount(String message, String title,  CoinCollection myCoins, Coin coinChoice)  throws NumberFormatException
    {
        String input = JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        int amount = Integer.parseInt(input);

        return amount;
    }

    /**
     * Prompts the user to add , remove, or end the program; as well as printing out current amounts and total
     *
     * @param myCoins - a reference to the current collection of coins
     * @return - the integer representing the choice the user made
     */
    private int getChoice(CoinCollection myCoins)
    {
        return JOptionPane.showOptionDialog(null,
                        myCoins + "\nSelect your action:",
                        "Add or remove?",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                        null, AddRemoveChoices.values(),
                        AddRemoveChoices.DONE);
    }

    /**
     * Prompts the user with a drop down menu to chose the coin they would like to change
     *
     * @return - the Coin selected
     * @throws NumberFormatException - if cancel or x is hit to end the program gracefully with final message
     */
    private Coin selectCoin() throws NumberFormatException
    {
        Coin testCoin = (Coin) JOptionPane.showInputDialog(null,
                "Select coin type",
                "Coin Selection",
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(),
                Coin.values(),
                Coin.values()[2]);

        if (testCoin == null)
            throw new NumberFormatException();

        return testCoin;
    }
}
