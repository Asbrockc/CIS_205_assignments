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
 * Class: CashRegister
 *
 * Description: Cash register class, emulates a cash register accepting transactions to practice
 * class building and encapsulation... I guess
 *
 * ****************************************
String+  ++ */
package lab06;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;

/**
 * A class representing a cash register,
 * Holds values based on scanned items, amount paid, and current cash in the draw
 * Once a transaction is initiated items can be scanned and the balance can be paid off
 */
public class CashRegister
{
    /**The name of the cashInDrawer sName, as a String*/
    private String sName;
    /**The amount of cashInDrawer in the sName, as a double*/
    private double cashInDrawer;
    /**The amount of money paid toward the current currentTransaction, as a double*/
    private double amountPaid;
    /**The current currentTransaction, as a Transaction.*/
    private Transaction currentTransaction;

    /**Every transaction on this shift*/
    private ArrayList<Transaction> transactions;
    /**Tracks summary stats over all prices in transaction*/
    private DoubleSummaryStatistics dss;

    /**
     * Constructor - initiates class variables to defualts values
     */
    public CashRegister()
    {
        this.sName = "Default";
        this.cashInDrawer = 0;
        this.amountPaid = 0;
        this.currentTransaction = null;

        this.transactions = new ArrayList<>();
        this.dss = new DoubleSummaryStatistics();
    }

    /**
     * Constructor overload - initiates defualt values + the name of the cash register
     *
     * @param sName - the name for the register
     */
    public CashRegister(String sName)
    {
        //initialize everything based on main constructor
        this();
        //rename with given name parameter
        this.sName = sName;
    }

    /**
     * Ditermines if the current register has an active transation going on
     * if it does it returns true; false if it is not active
     *
     * @return - true if the transaction is active (not null); false otherwise
     */
    public boolean isInTransaction()
    {
        //if there is no transaction initiated there is no transaction in progress
        if (this.currentTransaction == null)
            return false;

        return true;
    }

    /**
     * begins a shift for a new cashier and initiates the cash register with the passed in
     * amount of money
     *
     * @param initCash - amount to initiate the cash in draw to
     */
    public void startShift(double initCash)
    {
        this.cashInDrawer = initCash;
    }

    /**
     * finishes the current cashiers shift and retursn the final amount of cash in the drawer
     * prerequisite - if the current register is in the middle of a transaction returns -1, to signify that it can not
     * finish yet
     *
     * @return - the total cash in the draw at the end of the shift; or -1 if the register is mid transaction
     */
    public double finishShift()
    {
        //if its in the middle of a transaction return -1
        if (this.isInTransaction())
            return -1;

        //prints a summary report at the end of a shift
        this.printSummaryReport();

        return this.cashInDrawer;
    }

    /**
     * Starts a new transaction
     * prerequisite - the register is not already in a transation & there is cash in the drawer
     *
     * @return - true - if the transaction was successfully created; false otherwise
     */
    public boolean startTransaction()
    {
        //if there isnt already a transaction started and money available
        if (!this.isInTransaction() && this.cashInDrawer > 0)
        {
            //start a new transaction
            this.currentTransaction = new Transaction();
            return true;
        }

        return false;
    }

    /**
     * Finishes the current transaction
     * prerequisite - the cassh register is in a transaction & the amount owed is paid
     *
     * @return - true, if the balance is paid and the transaction is successfully closed; false otherwise
     */
    public boolean finishTransaction()
    {
        //if there is a transaction and no money owed
        if (this.isInTransaction() && (this.currentTransaction.getTransactionTotal() - this.amountPaid) == 0)
        {
            this.amountPaid = 0;

            //adds transation and total to arraylist and dss for end of shift report
            this.transactions.add(this.currentTransaction);
            this.dss.accept(this.currentTransaction.getTransactionTotal());

            //reset the transaction
            this.currentTransaction = null;
            return true;
        }

        //if not return false
        return false;
    }

    /**
     * Prints an end of summary report
     * Uses stored transactions to print out a full report on all transactions, min, max, average, and total prices
     */
    public void printSummaryReport()
    {
        System.out.println("\n----------END-OF-SHIFT REPORT----------");
        System.out.printf("CASH IN REGISTER: $%7.2f\n\n" , this.cashInDrawer);
        System.out.println("TRANSACTIONS:");
        for (int i = 0; i < this.transactions.size() ; i++)
        {
            System.out.println(" -" + (i+1) + ": " + this.transactions.get(i));
        }
        //formats min,max,average, and total into a list formed from the center; cleans it up and makes it more readable
        System.out.printf("\nSUMMARY:\n%22s $%.2f\n%22s $%.2f\n%22s $%.2f\n%22s $%.2f\n\n",
                "MIN TRANSACTION:",
                this.dss.getMin(),
                "MAX TRANSACTION:",
                this.dss.getMax(),
                "AVERAGE TRANSACTION:",
                this.dss.getAverage(),
                "TOTAL:",
                this.dss.getSum());
        System.out.println("----------------------------------------\n");
    }

    /**
     * scans a new item - adds the purchase amount onto the transaction total and increments the item count
     *
     * @param purchaseAmount - the amount of the purchase
     */
    public void scanItem(double purchaseAmount)
    {
        //wasn't asked but checks to see if the current transaction is available because nullpointer errors make me want to throw my computer
        if (this.currentTransaction != null)
            this.currentTransaction.addItemToTransaction(purchaseAmount);
    }

    /**
     * takes a payment and updates the registers values
     * also compares against current transaction for money owed, money payed, and total profit
     *
     * @param payment - the amount payed towards the transaction
     * @return - the change owed > 0; 0, if payed in full with no change; or the remaining balance < 0
     */
    public double collectPayment(double payment)
    {
        double moneyLeftOver = payment - this.getAmountOwed();

        //if there is money left over
        if (moneyLeftOver > 0)
        {
            //pay what is owed and return change
            this.amountPaid += this.getAmountOwed();
            this.cashInDrawer += this.getAmountOwed();
            return moneyLeftOver;
        }
        //if it is payed in full
        else if (moneyLeftOver == 0)
        {
            //make the payment and change will be 0
            this.amountPaid += payment;
            this.cashInDrawer += payment;
            return moneyLeftOver;
        }
        //if money is still owed
        else
        {
            //make the payment, and return amount still owed (negative number)
            this.cashInDrawer += this.getAmountOwed();
            this.amountPaid += payment;
            return moneyLeftOver;
        }
    }


    /**
     * Get current amount owed
     * based on transactions totals and the current amount payed towards it
     *
     * @return - the current amount of money owed; or 0 if the cash register does not have a transaction
     */
    public double getAmountOwed()
    {
        //if there is a transaction in progress return the amount owed on it (total - amount paid towards it)
        if (isInTransaction())
            return (this.currentTransaction.getTransactionTotal() - this.amountPaid);

        return 0;
    }


    /**
     * returns a computed string based on the current values of the register
     * in the format
     * CashRegister{sName='Register 1', cashInDrawer=100.0, currentTransaction=null, amountPaid=0.0}
     *
     * @return - the formateed string representation
     */
    @Override
    public String toString()
    {
        //CashRegister{sName='Register 1', cashInDrawer=100.0, currentTransaction=null, amountPaid=0.0}
        return "CashRegister{sName='" + this.sName +
                "', cashInDrawer=" + this.cashInDrawer +
                " currentTransaction=" + this.currentTransaction +
                ", amountPaid=" + this.amountPaid +
                "}";
    }

    /**
     * generalized equal operation for two cash registers - NOT USED
     *
     * @param obj - Another obj, tested to make sure it is a cash register as well
     * @return - true if all values between the two registers are maching values; false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CashRegister that = (CashRegister) obj;
        return Double.compare(that.cashInDrawer, cashInDrawer) == 0 &&
                Double.compare(that.amountPaid, amountPaid) == 0 &&
                Objects.equals(sName, that.sName) &&
                Objects.equals(currentTransaction, that.currentTransaction);
    }

    /**
     * sets the name of the current register
     *
     * @param sName - the new name for the register
     */
    public void setName(String sName)
    {
        this.sName = sName;
    }
}