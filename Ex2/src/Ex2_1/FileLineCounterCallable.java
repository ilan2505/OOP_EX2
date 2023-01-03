package Ex2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import static Ex2_1.Ex2_1.getNumOfLines;

/**
 * This class is an implement of Callable that we need to use ThreadPool in our function number 4 "getNumOfLinesThreadPool(String[] fileNames)".
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */
public class FileLineCounterCallable implements Callable<Integer> {
    private String fileName;        //name of the file
    private int numOfLines;         //number of lines

    /**
     * Constructor
     * @param fileName - Name of the file.
     */
    public FileLineCounterCallable(String fileName) {
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    /**
     * Call method for the forth function with ThreadPool.
     * @return - count_lines(fileName).
     */
    @Override
    public Integer call() throws Exception {
        return functions.count_lines(fileName);

    }
}
