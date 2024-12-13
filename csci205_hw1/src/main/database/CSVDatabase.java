/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2020
 * Instructor: Prof. Chris Dancy
 *
 * Name: Christopher Asbrock
 * Section: 11am
 * Date: 3/2/20
 * Time: 10:13 PM
 *
 * Project: csci205_hw1
 * Package: main
 * Class: CSVDatabase
 *
 * Description:
 *
 * ****************************************
 */
package main.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class CSVDatabase
{
    private enum Attribute
    {
        ID,
        NATIONALITY,
        DATE_OF_BIRTH,
        MARITAL_STATUS,
        NUMBER_OF_CHILDREN,
        WORK_REASON,
        CHARACTERISTICS,
        WORK_SKILL,
        REASON_FOR_LEAVE,
        CURRENT_JOB,
        PREVIOUS_JOB,
        START_DATE,
        CURRENT_START_DATE,
        END_DATE
    }

    private enum Order
    {
        ASSENDING(1), DECENDING(-1);

        private int order;

        Order(int i)
        {
            this.order = i;
        }

        public int getOrder()
        {
            return this.order;
        }
    }

    private final String DEFAULT_CSV_FILE = "src/main/database/defDB.csv";
    private ArrayList<Worker> workers; //temp till I  get the class to work with

    private String currentCSVFileTop;
    private String currentCSVFile;

    //so I don't accidentally overwrite the real one while testing
    private final String TEMP_CSV_FILE = "src/main/database/testDefDB.csv";


    public CSVDatabase() throws FileNotFoundException, IOException
    {
        this.workers = new ArrayList<>();
        this.currentCSVFile = DEFAULT_CSV_FILE;

        initDatabase();
        //basicBubbleSortIDTest();
        updateCSV();
    }

    private void initDatabase() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(this.currentCSVFile));
        String readLine = "";

        this.currentCSVFileTop = reader.readLine();

        while ((readLine = reader.readLine()) != null)
        {
            String [] workerAttributes = readLine.split(",");
            int i = 0;
            /*
            new Worker worker = new Worker(
            setID(workerAttributes[i++]),  //parse
            setNationality(workerAttributes[i++]), //string
            setDOB(workerAttributes[i++]), // Date
            setMaritalStatus(workerAttributes[i++]),
            setNumOfChildren(workerAttributes[i++]),
            setWorkReason(workerAttributes[i++]), //[]enum
            setCharacteristics(workerAttributes[i++]), // []enum
            setWorkSkill(workerAttributes[i++]), //[]enum
            setReasonForLeave(workerAttributes[i++]),
            setCurrentJob(workerAttributes[i++]),
            setPrevJob(workerAttributes[i++]),
            setStartDate(workerAttributes[i++]), //Date
            setCurrStartDate(workerAttributes[i++]), //Date
            setEndDate(workerAttributes[i++]), //Date
            );
            */
            Worker worker = new Worker(
            (workerAttributes[i++]), //parse
            (workerAttributes[i++]), //string
            (workerAttributes[i++]), // Date
            (workerAttributes[i++]),
            (workerAttributes[i++]),
            (workerAttributes[i++]), //[]enum
            (workerAttributes[i++]), // []enum
            (workerAttributes[i++]), //[]enum
            (workerAttributes[i++]),
            (workerAttributes[i++]),
            (workerAttributes[i++]),
            (workerAttributes[i++]), //Date
            (workerAttributes[i++]), //Date
            (workerAttributes[i++])//Date
            );

            this.workers.add(worker);

            //this.workers.add(readLine.split(","));
        }

        reader.close();
    }

    public ArrayList<Worker> sort(ArrayList arrayList, Attribute attribute, Order order)
    {
        ArrayList<Worker> listCopy = (ArrayList<Worker>)this.workers.clone();

        switch (attribute)
        {
            case ID:
                QuickSort.sort(listCopy, (a,b) ->
                {
                    return order.getOrder() * ((Integer.valueOf(((Worker)a).getOne()[0])) - (Integer.valueOf(((Worker)b).getOne()[0])));
                });
                break;
            case NATIONALITY:
                QuickSort.sort(listCopy, (a,b) ->
                {
                    int value = order.getOrder() * (((Worker)a).getOne()[1]).compareTo((((Worker)b).getOne()[1]));

                    if (value != 0)
                        return value;
                    else
                        return order.getOrder() * ((Integer.valueOf(((Worker)a).getOne()[0])) - (Integer.valueOf(((Worker)b).getOne()[0])));
                });
                break;
            case DATE_OF_BIRTH:
                break;
            case MARITAL_STATUS:
                break;
            case NUMBER_OF_CHILDREN:
                break;
            case WORK_REASON:
                break;
            case CHARACTERISTICS:
                break;
            case WORK_SKILL:
                break;
            case REASON_FOR_LEAVE:
                break;
            case CURRENT_JOB:
                break;
            case PREVIOUS_JOB:
                break;
            case START_DATE:
                break;
            case CURRENT_START_DATE:
                break;
            case END_DATE:
                break;
        }

        return listCopy;
    }

    private void updateCSV() throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.TEMP_CSV_FILE));
        String completeString = this.currentCSVFileTop + "\n";

        ArrayList<Worker> temp = this.sort(this.workers, Attribute.NATIONALITY, Order.ASSENDING);

        for (int i = 0; i < temp.size(); i++)
        {
            int j = 0;
            completeString +=
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "," +
                (temp.get(i).getOne()[j++]) + "\n";
        }
        /*
        for (int i = 0; i < this.workers.size(); i++)
        {
            completeString +=
                getID(workerAttributes[i++]) + "," +
                getNationality(workerAttributes[i++]) + "," +
                getDOB(workerAttributes[i++]) + "," +
                getMaritalStatus(workerAttributes[i++]) + "," +
                getNumOfChildren(workerAttributes[i++]) + "," +
                getWorkReason(workerAttributes[i++]) + "," +
                getCharacteristics(workerAttributes[i++]) + "," +
                getWorkSkill(workerAttributes[i++]) + "," +
                getReasonForLeave(workerAttributes[i++]) + "," +
                getCurrentJob(workerAttributes[i++]) + "," +
                getPrevJob(workerAttributes[i++]) + "," +
                getStartDate(workerAttributes[i++]) + "," +
                getCurrStartDate(workerAttributes[i++]) + "," +
                getEndDate(workerAttributes[i++]) + "\n";
        }*/

        writer.write(completeString);
        writer.close();
    }

    public static void main(String [] args)
    {
        try
        {
            CSVDatabase csvDatabase = new CSVDatabase();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}