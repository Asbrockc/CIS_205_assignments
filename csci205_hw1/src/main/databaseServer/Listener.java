/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/4/20
 * Time: 11:28 PM
 *
 * Project: csci205_hw1
 * Package: main.databaseServer
 * Class: Listener
 *
 * Description:
 *
 * ****************************************
 */
package main.databaseServer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Listener extends Thread
{
    private final Scanner SCANNER;
    private Socket socket;
    private final PrintStream PRINTER;


    public Listener(Socket socket, ServerSocket serverReference) throws IOException
    {
        this.SCANNER = new Scanner(socket.getInputStream());
        this.PRINTER = new PrintStream(socket.getOutputStream());
    }

    public void run()
    {
        try
        {
            while (true)
            {
                if (!this.SCANNER.hasNextLine())
                    throw new Exception("Connection Lost");

                String[] input = this.SCANNER.nextLine().split("");

                switch (input[0])
                {
                    default:
                        System.out.println("somthing Caught -> " + input[0]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            this.SCANNER.close();
        }
    }
}