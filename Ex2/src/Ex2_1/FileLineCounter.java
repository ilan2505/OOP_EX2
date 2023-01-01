package Ex2_1;
import java.io.*;
import java.util.Scanner;

public class FileLineCounter extends Thread {
    private String fileName;
    private int numOfLines;

    public FileLineCounter(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    public void run() {
        this.numOfLines = functions.count_lines(fileName);
    }
    public int getNumOfLines() {
        return numOfLines;
    }
}
