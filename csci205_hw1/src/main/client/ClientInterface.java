/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/4/20
 * Time: 11:44 PM
 *
 * Project: csci205_hw1
 * Package: main.client
 * Class: ClientInterface
 *
 * Description:
 *
 * ****************************************
 */
package
        main.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientInterface
{
    private Socket socket;
    private Scanner networkInput;
    private PrintStream networkOutput;

    public ClientInterface(String host, int port) throws IOException
    {
        this.socket = new Socket(host, port);

        this.networkInput = new Scanner(this.socket.getInputStream());
        this.networkOutput = new PrintStream(this.socket.getOutputStream());
    }

    public void run()
    {
        Scanner test = new Scanner(System.in);

        while (true)
        {
            System.out.println("TYPE WHAT TO SEND: ");
            String string = test.nextLine();

            if (string.equalsIgnoreCase("END"))
                break;

            this.networkOutput.print(string);
        }
    }

    public static void main(String[]args) throws IOException
    {
        ClientInterface client = new ClientInterface("75.97.101.190",5000);
        client.run();
    }
}