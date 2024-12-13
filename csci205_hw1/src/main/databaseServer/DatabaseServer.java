/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/4/20
 * Time: 11:09 PM
 *
 * Project: csci205_hw1
 * Package: main.databaseServer
 * Class: DatabaseServer
 *
 * Description:
 *
 * ****************************************
 */
package main.databaseServer;

import main.database.SortUtil;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DatabaseServer
{
    private ServerSocket server;
    private Socket socket;

    public DatabaseServer(int port) throws IOException
    {
        this.server = new ServerSocket(port);
        System.out.println("Waiting On Connection...");
        this.socket = this.server.accept();
        System.out.println("Connected");

        Listener listener = new Listener(this.socket, this.server);
        listener.start();
    }

    public static void main(String[] args) throws IOException
    {
        DatabaseServer server = new DatabaseServer(5000);
    }
}