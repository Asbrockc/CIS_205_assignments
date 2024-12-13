/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/23/20
 * Time: 1:36 AM
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: Account
 *
 * Description:
 *
 * ****************************************
 */
package lab10;

import java.io.PrintStream;

/**
 * account class to handle money
 */
public class Account
{
    /**current balance*/
    private double balance;

    /**
     * basic constructor, initializes initial balance
     *
     * @param initBalance - initial balance
     */
    public Account(double initBalance)
    {
        this.balance = initBalance;
    }

    /**
     * adds money too account
     *
     * @param amount - the amount to add
     */
    public void credit(double amount)
    {
        this.balance += amount;
    }

    /**
     * takes money from account
     *
     * @param amount - amount to remove
     * @throws InsufficientFundsException - if there is not enough funds
     */
    public void debit(double amount) throws InsufficientFundsException
    {
        if (amount <= this.balance)
            this.balance -= amount;
        else
            throw new InsufficientFundsException(String.format(
                    "<Not Enough Funds> " +
                     "Current Balance: " +
                    "$%.2f" +
                    " | Requested Amount: " +
                    "$%.2f", this.balance, amount));
    }

    /**
     * processes a check from any type of payable
     * calculate pay will use the calculate pay defined by the class
     *
     * @param payee - person getting payed
     * @param hoursBilled - hours worked
     * @param out - output for bill
     * @throws InsufficientFundsException - if pay is more then money in account
     */
    public void processCheck(Payable payee, double hoursBilled, PrintStream out) throws InsufficientFundsException
    {
        out.println(String.format("%-15s" + payee.getPayTo() +
                "%-15s" + payee.getPayMemo() +
                "%-15s%.2f",
                "Pay to:",
                "\nPay memo:",
                "\nPay amount:",
                payee.calculatePay(hoursBilled)));
        this.debit(payee.calculatePay(hoursBilled));
    }

    /**
     * gets the account balance
     *
     * @return - current balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * returns the account balance
     *
     * @return - compute string representing the current balance
     */
    @Override
    public String toString()
    {
        return "Account: " +
                "balance=" + balance;
    }
}

/**
 * exception for insufficient funds if tring to access money that is not there
 */
class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(String message)
    {
        super(message);
    }
}