package net.itorbit.pesaram.filemanager.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class helps to easily create a file and write data into it.
 * @author <a href="mailto:a.rahmani@itorbit.net">Amir Mohammad</a>
 */
public class FileUtils {
    public static void createFile(String filePath) throws IOException {

        File file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void writeFile(String filePath, String data) throws IOException {
        FileWriter myWriter = new FileWriter(filePath);
        myWriter.write(data);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

}
