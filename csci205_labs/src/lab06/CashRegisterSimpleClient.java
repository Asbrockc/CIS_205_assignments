/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 2/6/20
 * Time: 1:23 AM
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: CashRegisterSimpleClient
 *
 * Description: Instructor given test code for CashRegister & Transaction classes
 *
 * ****************************************
 */
package lab06;

public class CashRegisterSimpleClient
{
    public static void main(String[] args)
    {
        // First, let's evaluate a transaction
        Transaction trans = new Transaction();
        trans.addItemToTransaction(10);
        trans.addItemToTransaction(2.50);
        trans.addItemToTransaction(9.75);
        System.out.println("Testing Transaction:\n" + trans);
        // Now, let's evaluate one cash registers
        CashRegister reg = new CashRegister("Register 1");
        reg.setName("Register 1");
        reg.startShift(100.0);
        System.out.println("reg: New transaction: $2.50, $9.95, $5.50 = $17.95");
        reg.startTransaction();
        reg.scanItem(2.50);
        reg.scanItem(9.95);
        reg.scanItem(5.50);
        double amtBack = reg.collectPayment(15.0);
        amtBack = reg.collectPayment(10);
        reg.finishTransaction();
        System.out.println("reg: New transaction: $10, $7.50, $19.95, $5 = $42.45");
        reg.startTransaction();
        reg.scanItem(10);
        reg.scanItem(7.50);
        reg.scanItem(19.95);
        reg.scanItem(5.00);
        amtBack = reg.collectPayment(42.45);
        reg.finishTransaction();
        System.out.println("reg: Finishing the shift\n");
        reg.finishShift();
        System.out.println("\nreg: Should be empty:\n" + reg);
    }
}