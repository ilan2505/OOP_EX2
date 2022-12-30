package main.java;

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
        int count = 0;
        File file = new File(fileName);
        synchronized (file) {
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    count++;
                    reader.nextLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
            numOfLines = count;
        }
    }
}
