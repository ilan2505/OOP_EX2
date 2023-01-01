package Ex2_1;
import java.io.*;

public class functions  {
    //help function 1 -----------------------------------------------------------------------------
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

    //help function 2 ---------------------------------------------------------------------------
    public static String create_files(int num_file) {
        String fileName = "file_" + num_file + ".txt";
        try {
            File file = new File(fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileName;
    }

    //help function 3 -----------------------------------------------------------------------------
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
