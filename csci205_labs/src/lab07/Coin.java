package lab07;

/**
 * enum representing different coins
 *
 * @author Christopher Asbrock
 */
public enum Coin
{
    /**values representing coins*/
    PENNY(0.01), NICKEL(0.05), DIME(0.10), QUARTER(0.25), DOLLAR (1);

    /**double to hold the value of the coin*/
    private double value;

    /**
     * default constructor
     */
    Coin()
    {
        value = 0;
    }

    /**
     * constructor to set the value of the enum coin
     *
     * @param value - coin value
     */
    Coin(double value)
    {
        this.value = value;
    }

    /**
     * getter for coin value
     *
     * @return - this instances value
     */
    public double getValue()
    {
        return this.value;
    }
}
