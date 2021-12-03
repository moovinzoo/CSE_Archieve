import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileIOPractice {
    public static void main(String[] args) {
        String[] lines = {
            "first line",
            "second line",
            "third line"
        };
        String filename = "file-io-practice.txt";
        writeLines(filename, lines);
        List<String> lineList = readLines(filename);
        System.out.println(lineList);
    }

    private static List<String> readLines(String filename) {
        List<String> lines = new LinkedList<>();
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static void writeLines(String filename, String[] lines) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
