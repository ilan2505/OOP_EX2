package Ex2_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * This class creates several text files and calculate the total number of lines in these files.
 * We will use three methods to do this mission :
 * 1. Normal method without Threads.
 * 2. Method with Threads.
 * 3. Method with ThreadPool.
 * The class includes method main to check that everything is working properly.
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */

public class Ex2_1 {
    
    /**
     * This method creates n text files on disk and returns an array of the file names.
     * In each line we write a sentence : "Hello World".
     * @param n - A natural number representing the number of text files.
     * @param seed - Seed for random. (a defined set that will give the same result each time)
     * @param bound - Maximum number of lines in one file.
     * @return files_names - An array of file names.
     */
    public static String[] createTextFiles(int n, int seed, int bound){
        String[] files_names = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            int numOfLines = rand.nextInt(bound);       //to give randomly number of lines in file_X.txt
            files_names[i] = functions.create_files(i);
            functions.write_files(files_names[i], numOfLines);
        }
        return files_names;
    }

    /**
     * This method gives the total number of lines in these files.
     * This method doesn't use any thread.
     * @param fileNames - An array that contains the file names.
     * @return numOfLines - total number of lines in these files.
     */
    public static int getNumOfLines(String[] fileNames){
        int numOfLines = 0;
        for (String file_name : fileNames) {
            numOfLines += functions.count_lines(file_name);
        }
        return numOfLines;
    }

    /**
     * This method gives the total number of lines in these files.
     * This method uses Threads.
     * @param fileNames - An array that contains the file names.
     * @return numOfLines - total number of lines in these files.
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        FileLineCounter[] threads = new FileLineCounter[fileNames.length];
        int numOfLines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new FileLineCounter(fileNames[i]);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threads.length; i++) {
            numOfLines += threads[i].getNumOfLines();
        }

        return numOfLines;
    }

    /**
     * This method gives the total number of lines in these files.
     * This method uses ThreadPool.
     * @param fileNames - An array that contains the file names.
     * @return numOfLines - total number of lines in these files.
     */
    public static int getNumOfLinesThreadPool(String[] fileNames){
        int numOfLines = 0;
        ExecutorService pool = Executors.newFixedThreadPool(fileNames.length);
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
        ArrayList<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {
            tasks.add(new FileLineCounterCallable(fileNames[i]));
        }

        try {
            results = (ArrayList<Future<Integer>>) pool.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
        try {
            if(!pool.awaitTermination(100, TimeUnit.MILLISECONDS)){
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            e.printStackTrace();
        }

        for (Future<Integer> result: results){
            try {
                numOfLines += result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return numOfLines;
    }


    //----------------------------------MAIN-----------------------------------------------------
    /**
     * This main checks that everything is working properly with our 3 methods.
     * We can count how long time it takes to run each method.
     */
    public static void main(String[] args) {
        int start_time, end_time;
        String file_names[] = createTextFiles(100, 42, 99999);

        System.out.println("[Normal Example]");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLines(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time) + "\n");

        System.out.println("[Example using Threads]");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLinesThreads(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time) + "\n");

        System.out.println("[Example using Threads Pool]");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLinesThreadPool(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));
    }
}
