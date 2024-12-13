package lab10;

import lab10.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest
{
    /**bypass float point inaccuracies*/
    private static final double EPSILON = 1.0E-12;

    private static final double EMPTY_ACCOUNT = 0;

    private ArrayList<Payable> workers;
    private Account account;

    @BeforeEach
    void setUp() throws ParseException, lab10.Manager.ManagerException
    {
        account = new Account(1000);
        workers = new ArrayList<>(
                Arrays.asList(
                        new Employee(1, "Spongebob", "Squarepants", 1112229999, lab10.HRDateUtils.strToDate("1933-02-01"),0.49 ),
                        new Employee(10, "Patric", "Star", 1001002000, lab10.HRDateUtils.strToDate("1999-07-19"),0.01 ),
                        new Employee(2, "Squidward", "Tentacles", 110203000, lab10.HRDateUtils.strToDate("1100-05-04"),0.48 ),
                        new Employee(1, "Spongebob", "Squarepants", 1322229999, lab10.HRDateUtils.strToDate("1933-02-01"),0.49 ),
                        new Employee(10, "Patric", "Star", 1651002000, lab10.HRDateUtils.strToDate("1999-07-19"),0.01 ),
                        new Employee(2, "Squidward", "Tentacles", 133203000, lab10.HRDateUtils.strToDate("1100-05-04"),0.48 )
                ));
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void credit()
    {
        //grab the start amount the account has and make a payinto test it
        double initAmount = this.account.getBalance();
        double credit = 250;

        this.account.credit(credit);

        //make sure the amount credited is the amount added
        assertEquals(initAmount + credit, this.account.getBalance(), EPSILON);
    }

    @Test
    void debit() throws InsufficientFundsException
    {
        //keep track of inital amounts and amount to pay
        double initAmount = this.account.getBalance();
        double payout = 250;

        this.account.debit(payout);

        //make sure the correct amount was removed
        assertEquals(initAmount - payout, this.account.getBalance(), EPSILON);

        //remove the rest to make sure it will empty all the way to 0 left
        this.account.debit(this.account.getBalance());

        assertEquals(EMPTY_ACCOUNT, this.account.getBalance(), EPSILON);
    }

    @Test
    void debitException()
    {
        //make a payment more then the initial $1000
        double payout = 1200;

        //makes sure it throws the exception
        assertThrows(InsufficientFundsException.class, () -> this.account.debit(payout));

    }

    @Test
    void processCheck() throws InsufficientFundsException
    {
        //initialize hours worked and get what the pay should be
        int hours = 40;
        double totalPay = ((Employee) workers.get(0)).calculatePay(hours);
        double totalAvailable = this.account.getBalance();

        //process the check from the worker
        this.account.processCheck(workers.get(0), hours, System.out);

        //makes sure the right amount was removed
        assertEquals(totalAvailable - totalPay, this.account.getBalance(), EPSILON);

    }

    @Test
    void processCheckException() throws InsufficientFundsException
    {
        //empty the account so nothing is left
        this.account.debit(this.account.getBalance());

        //test any worker, the account is empty so any pay should throw an exception
        assertThrows(InsufficientFundsException.class,  () -> this.account.processCheck(workers.get(0), 40, System.out));
    }
}