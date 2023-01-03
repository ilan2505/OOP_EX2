package Ex2_1;
import java.io.*;

/**
 * This class contains helps functions that we use in our Ex2_1 class.
 * @author Jonatan Boritsky : 207254194,  Ilan Meyer Souffir : 342615648
 */

public class functions  {
    
    /**
     * This method opens a file and counts the lines inside the file.
     * @param file_name - Name of the file that we open.
     * @return count - Number of lines inside the file.
     */
    public static int count_lines(String file_name) {
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

    /**
     * This method creates a new file with the name "file_X".
     * @param num_file - Which file we create.
     * @return fileName - Name of the file.
     */
    public static String create_files(int num_file) {
        String fileName = "file_" + num_file + ".txt";
        return fileName;
    }

    /**
     * This method writes "Hello World" in each line of the file.
     * @param file_name - Name of the file where we write.
     * @param num_lines - Number of the lines in the file.
     */
    public static void write_files(String file_name, int num_lines) {
        try {
            File file = new File(file_name);
            file.deleteOnExit();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (int i = 0; i < num_lines; i++) {
                writer.write("Hello World" + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
