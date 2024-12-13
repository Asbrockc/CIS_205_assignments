/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 *
 * Name: Christopher Asbrock
 * Date: 02/10/2020
 * Time: 11:55pm
 *
 * Project: csci205_labs
 * Package: lab07
 * Class: 11am
 * Description:
 *
 * This exercise is designed to teach the student about enumerated types, a
 * powerful class mechanism in Java, particularly when compared to enumerations
 * in other languages. The student steps through creating a CoinCollection
 * class, adding several capabilities throughout the lab exercise.
 *
 * Prof. Brian King 2014-fall, modified 2015-spring
 */
package lab07;

import java.util.Arrays;

/**
 * A class to represent a collection of coins. The coins that can be stored are
 * determined by the enumerated type {@link Coin}.
 *
 * @author Prof. Brian King
 * @author Christopher Asbrock
 */
public class CoinCollection
{
    /**array of integers contains actual count of each coin type in the collection*/
    private final int coinCount[];

    /**
     * Constructor to create a new empty coin collection. No coins are in the
     * collection when created.
     */
    public CoinCollection()
    {
        this.coinCount = new int[Coin.values().length];
    }

    /**
     * Add coins to the collection
     *
     * @param coinType The type of coin to add
     * @param count The number of coins to add of the specified type
     */
    public void addCoins(Coin coinType, int count)
    {
        this.coinCount[coinType.ordinal()] += count;
    }

    /**
     * Remove coins from the collection
     *
     * @param coinType The type of coin to remove
     * @param count the number of coins to remove
     * @return the number of coins actually removed, ensuring that we can't
     * remove more coins than we have, preventing negative coin amounts.
     */
    public int removeCoins(Coin coinType, int count)
    {
        int excessCoins = this.coinCount[coinType.ordinal()] - count;

        this.coinCount[coinType.ordinal()] -= count;

        if (excessCoins < 0)
        {
            this.coinCount[coinType.ordinal()] = 0;
            //excessCoins is negative so adding it will remove them from the total
            return count + excessCoins;
        }
        else
            return count;

    }

    /**
     * Return the count of the specified coin type in the collection
     *
     * @param coinType the coin type to return the count for
     * @return the number of coins of the specified type
     */
    public int getCount(Coin coinType)
    {
        return this.coinCount[coinType.ordinal()];
    }

    /**
     * Compute the total amount of the collection
     *
     * @return the total amount of the collection in dollars
     */
    public double getTotal()
    {
        double totalAmount = 0;

        for (int i = 0; i < this.coinCount.length; i++)
        {
            for (int j = 0; j < this.coinCount[i]; j++)
            {
                totalAmount += Coin.values()[i].getValue();
            }

        }

        return totalAmount;
    }

    /**
     * returns a formated string to denote coins and the amount there is of each
     *
     * @return - computed string
     */
    @Override
    public String toString()
    {
        String message = "";

        for (int i = 0; i < Coin.values().length; i++)
        {
            message += String.valueOf(Coin.values()[i]) +
                        ": " +
                        String.valueOf(this.coinCount[i]) +
                        "\n";
        }

        message += "TOTAL = $" + String.format("%.2f", this.getTotal());

        return message;
    }

    /**
     * Main program designed to test out the CoinCollection class
     */
    public static void main(String args[])
    {
        CoinCollection myCoins = new CoinCollection();

        myCoins.addCoins(Coin.NICKEL, 5);
        myCoins.addCoins(Coin.DIME, 3);
        myCoins.addCoins(Coin.QUARTER, 7);

        System.out.println(myCoins);

        System.out.println("TEST: Removing 10 nickels, only 5 to remove ");
        System.out.printf("ACTUAL: Removed %d nickels%n",
                          myCoins.removeCoins(Coin.NICKEL, 10));

        System.out.println(myCoins);

        System.out.println("TEST: Removing 1 quarter");
        System.out.printf("RESPONSE: Removed %d quarter%n",
                          myCoins.removeCoins(Coin.QUARTER, 1));

        System.out.println(myCoins);
    }

}
