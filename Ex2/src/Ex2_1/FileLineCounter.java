package Ex2_1;
import java.io.*;
import java.util.Scanner;

/**
 * This class extends Thread that we need to use threads in our function number 3 "getNumOfLinesThreads(String[] fileNames)".
 * @author Jonatan Boritsky,  Ilan Meyer Souffir
 */
public class FileLineCounter extends Thread {
    private String fileName;       //name of the file
    private int numOfLines;        //number of lines

    /**
     * Constructor
     * @param fileName - Name of the file.
     */
    public FileLineCounter(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    /**
     * Run method for the third function with threads.
     */
    public void run() {
        this.numOfLines = functions.count_lines(fileName);
    }
    
    /**
     * This method gives the num of lines into threads.
     * @return - numOfLines.
     */
    public int getNumOfLines() {
        return numOfLines;
    }
}
