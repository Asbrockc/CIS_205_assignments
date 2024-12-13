/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/4/20
 * Time: 9:22 AM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Transaction
 *
 * Description:Transaction class used by a cash register, keeps track of items and prices
 *
 * ****************************************
 */
package lab06;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * a class representing one overall transaction for a cash register
 *
 * Keeps track of total money owed on transaction and number of items in the transaction
 */
public class Transaction
{
    /**ArrayList to hold all transactions*/
    private ArrayList<Double> itemList;
    /**Tracks summary stats over all prices in transaction*/
    private DoubleSummaryStatistics dss;

    /**
     * Constructor - initiates numItems and transaction total to 0
     * moved up here where it belongs
     */
    public Transaction()
    {
        this.itemList = new ArrayList<>();
        this.dss = new DoubleSummaryStatistics();
    }

    /**
     * Gets current number of items in transaction
     *
     * @return - total number of items
     */
    public long getNumItems()
    {
        return this.dss.getCount();
    }

    /**
     * returns list of all prices added to this transaction
     *
     * @return - List object of all costs
     */
    public List<Double> getListOfPrices()
    {
        return itemList;
    }

    /**
     * gets the total cost of current transaction
     *
     * @return - total cost
     */
    public double getTransactionTotal()
    {
        return this.dss.getSum();
    }

    /**
     * returns the price of the item that costs the least
     *
     * @return - the min price
     */
    public double getMinCost()
    {
        return this.dss.getMin();
    }

    /**
     * reutrns the price of the item that costs the most
     *
     * @return - the max price
     */
    public double getMaxCost()
    {
        return this.dss.getMax();
    }

    /**
     * calculates the average of all the costs from this transaction
     *
     * @return - calculated average off prices
     */
    public double getAverageCost()
    {
        return this.dss.getAverage();
    }

    /**
     * Adds an item to transaction
     * Increments number of items and adds price to total
     *
     * @param price - price off current item being added
     */
    public void addItemToTransaction(double price)
    {
        this.itemList.add(price);
        this.dss.accept(price);
    }


    /**
     * a string representation of Transaction
     * Notes the current number of items and current total
     *
     * @return - computed string value
     */
    @Override
    public String toString()
    {
        return "Transaction{itemList="+ this.itemList +
                ", dss=" + this.dss +
                "}";
    }

}