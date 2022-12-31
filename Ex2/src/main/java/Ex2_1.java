package main.java;

import java.io.*;
import java.util.Random;

public class Ex2_1 {
    //fonction qui crer un text avec un nombre aleatoire de lignes avec ecrit "Hello World" par ligne.
    //n = nombre de fichier txt creer
    //bound = nombre random de "hello word" dans le texte mais au max c'est bound (se compte en ligne)
    //seed = garde un set defini qui donnera a chaque fois le meme resulat.
    public static String[] createTextFiles(int n, int seed, int bound){
        String[] files_names = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            int numOfLines = rand.nextInt(bound);
            files_names[i] = create_files(i);
            write_files(files_names[i], numOfLines);
        }
        return files_names;
    }

    //fonction 2
    public static int getNumOfLines(String[] fileNames){
        int num_lines = 0;
        for (String file_name : fileNames) {
            num_lines += countLines(file_name);
        }
        return num_lines;
    }


    //fonction 3
    public int getNumOfLinesThreads(String[] fileNames){
        return 0;
    }

    //fonction 4
    public int getNumOfLinesThreadPool(String[] fileNames){
        return 0;
    }


    //help function 1
    private static int countLines(String file_name) {
        int count = 0;
        try {
            File file = new File(file_name);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (( reader.readLine()) != null) {
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    //help function 2
    private static String create_files(int num_file) {
        String fileName = "file_" + num_file + ".txt";
        try {
            File file = new File(fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileName;
    }

    //help function 3
    private static void write_files(String file_name, int num_lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
            for (int i = 0; i < num_lines; i++) {
                writer.write("Hello World" + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int start_time, end_time;
        String file_names[] = createTextFiles(20, 5, 220);

        System.out.println("*Exemple 1*");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLines(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));
    }
}
