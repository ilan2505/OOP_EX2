package Ex2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import static Ex2_1.Ex2_1.getNumOfLines;

public class FileLineCounterCallable implements Callable<Integer> {
    private String fileName;
    private int numOfLines;

    public FileLineCounterCallable(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    @Override
    public Integer call() throws Exception {
        return functions.count_lines(fileName);

    }
}
