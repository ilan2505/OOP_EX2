package main;
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
    public int getNumOfLinesThreads(String[] fileNames){
        return 0;
    }

    //fonction 4
    public int getNumOfLinesThreadPool(String[] fileNames){
        return 0;
    }


    //----------------------------------MAIN-----------------------------------------------------
    public static void main(String[] args) {
        int start_time, end_time;
        String file_names[] = createTextFiles(5, 5, 220);

        System.out.println("-----------------------Exemple 1-----------------------");
        start_time = (int) System.currentTimeMillis();
        System.out.println("Number of lines : " + getNumOfLines(file_names));
        end_time = (int) System.currentTimeMillis();
        System.out.println("Time in milliseconds : " + (end_time - start_time));
    }
}