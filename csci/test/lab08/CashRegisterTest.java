package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest
{
    /**or tests that use doubles to bypass float point inaccuracies*/
    private static final double EPSILON = 1.0E-12;

    /** cashresiter used in every test */
    private CashRegister register;

    /**set array size for various List tests*/
    private final int SIZE = 10;

    @BeforeEach
    void setUp()
    {
        this.register = new CashRegister();
    }

    @AfterEach
    void tearDown()
    {
        //new register is instantiated before each so no need to reset anything
    }

    @DisplayName("getPurchaseCount: count test")
    @Test
    void getPurchaseCount()
    {
        //test the initial state. it should have NO items
        assertEquals(0,register.getPurchaseCount());

        //now an example with 2 items scanned
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(2, register.getPurchaseCount());
    }

    @DisplayName("getListOfPurchasesSize: Items added to list test")
    @Test
    void getListOfPurchasesSize()
    {
        //test the initial state. it should have NO items
        assertEquals(0,register.getListOfPurchases().size());

        //now an example with 2 items scanned
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(2, register.getListOfPurchases().size());
    }

    @DisplayName("getListOfPurchasesContent: Items in list correct test")
    @Test
    void getListOfPurchasesContent()
    {
        assertEquals(0,register.getListOfPurchases().size());

        int [] testPrices = new int[SIZE];
        int testPrice = 0;

        //scans in random variables, then logs the same random variables
        for (int i = 0; i < SIZE; i++)
        {
            testPrice = new Random().nextInt(10);
            register.scanItem(testPrice);
            testPrices[i] = testPrice;
        }

        //loops through and makes sure they where correctly scanned in
        for (int i = 0; i < this.register.getListOfPurchases().size(); i++)
        {
            assertEquals(testPrices[i], this.register.getListOfPurchases().get(i));
        }

    }

    @DisplayName("getTransactionTotal: totaling transactions test")
    @Test
    void getTransactionTotal()
    {
        assertEquals(0.0, register.getTransactionTotal(), EPSILON);

        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(1.82, register.getTransactionTotal(), EPSILON);
    }

    @DisplayName("getPaymentCollected: collecting various payments test")
    @Test
    void getPaymentCollected()
    {
        assertEquals(0.0, register.getPaymentCollected(), EPSILON);
        register.collectPayment(Money.TEN, 1);

        assertEquals(Money.TEN.getValue(), register.getPaymentCollected(), EPSILON);

        register.collectPayment(Money.TEN, 3);

        assertEquals((Money.TEN.getValue() * 4), register.getPaymentCollected(), EPSILON);
    }

    @DisplayName("scanItem: scanning in new items test")
    @Test
    void scanItem()
    {
        assertEquals(this.register.getTransactionTotal(), 0, EPSILON);

        double priceOne = 2.30;
        double priceTwo = 4.70;

        this.register.scanItem(priceOne);
        this.register.scanItem(priceTwo);

        assertEquals(this.register.getTransactionTotal(), (priceOne + priceTwo), EPSILON);
    }

    @DisplayName("scanItemException: Bad Scan test")
    @Test
    void scanItemException()
    {
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(-0.5));

        assertThrows(IllegalArgumentException.class, () -> register.scanItem(10000.0));
    }

    @DisplayName("collectPaymentException: not enough funds exception test")
    @Test
    void collectPaymentException()
    {
        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(Money.DOLLAR, -1));

        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(Money.DOLLAR, 100000));

        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(null, 1));
    }

    @DisplayName("collectPayment: collecting payment test")
    @Test
    void collectPayment()
    {
        assertEquals(this.register.getPaymentCollected(), 0, EPSILON );

        this.register.collectPayment(Money.TEN, 1);
        this.register.collectPayment(Money.FIVE, 1);
        this.register.collectPayment(Money.DOLLAR, 1);

        assertEquals(this.register.getPaymentCollected(),
                (Money.TEN.getValue() + Money.FIVE.getValue()  + Money.DOLLAR.getValue()),
                EPSILON );

    }

    @DisplayName("giveChange: correct change test")
    @Test
    void giveChange()
    {
        double priceOne = 9.23;

        register.scanItem(priceOne);
        register.collectPayment(Money.TEN, 1);

        try
        {
            assertEquals(this.register.giveChange(), (Money.TEN.getValue() - priceOne), EPSILON);
        }
        catch (ChangeException e)
        {
            //forces try/catch, exception is already tested in another test, but if it does get to here for some
            //unknown reason I would want to know about it: so this test auto fails just for getting here
            assertEquals(true,false);
        }

    }

    @DisplayName("giveChangeException: correct change exception test")
    @Test
    void giveChangeException()
    {
        double priceOne = 9.23;

        register.scanItem(priceOne);

        assertThrows(ChangeException.class, () -> register.giveChange());
    }

    @DisplayName("testEquals: tests that two registers are correctly compared")
    @Test
    void testEquals()
    {
        CashRegister registerTwo = new CashRegister();
        double priceOne = 9.23;
        double priceTwo = 1.50;

        assertTrue(registerTwo.equals(this.register));

        this.register.scanItem(priceOne);
        this.register.scanItem(priceTwo);

        assertFalse(registerTwo.equals(this.register));

        registerTwo.scanItem(priceOne);
        registerTwo.scanItem(priceTwo);

        assertTrue(registerTwo.equals(this.register));

    }
}