package Ex2_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {
    //fonction qui crer un text avec un nombre aleatoire de lignes avec ecrit "Hello World" par ligne.
    //n = nombre de fichier txt creer
    //bound = nombre random de "hello word" dans le texte mais au max c'est bound (se compte en ligne)
    //seed = garde un set defini qui donnera a chaque fois le meme resulat.
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

    //fonction 2
    public static int getNumOfLines(String[] fileNames){
        int numOfLines = 0;
        for (String file_name : fileNames) {
            numOfLines += functions.count_lines(file_name);
        }
        return numOfLines;
    }

    //fonction 3
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

    //fonction 4
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
    public static void main(String[] args) {
        int start_time, end_time;
        String file_names[] = createTextFiles(1000, 42, 500);

        System.out.println("-----------------------Exemple 1-----------------------");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLines(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));

        System.out.println("-----------------------Exemple 1 using threads-----------------------");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLinesThreads(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));

        System.out.println("-----------------------Exemple 1 using threads pool-----------------------");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLinesThreadPool(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));
    }
}