package week_1.day_3;

import java.io.*;
import java.nio.file.*;

public class Main {
    
    // write to a file
    public static void writeFile(String filename, String content) throws IOException {
        Files.writeString(Path.of(filename), content);       
    }

    // read from a file
    public static String readFile(String filename) throws IOException {
        return Files.readString(Path.of(filename));
    }

    // append to a file
    public static void appendFile(String filename, String line) throws IOException {
        Files.writeString(
            Path.of(filename), 
            line + "\n",
            StandardOpenOption.APPEND, StandardOpenOption.CREATE
        );
    }

    public static void main(String[] args) {
        String filename = "example.txt";
        String content = "Hello, this is a test file yaa.";

        try {
            // Write to the file
            writeFile(filename, content);
            System.out.println("File written successfully.");

            // Read from the file
            String readContent = readFile(filename);
            System.out.println("Read from file: " + readContent);

            // Append to the file
            appendFile(filename, "This line is appended.");
            System.out.println("Line appended successfully.");

            // Read again to see the appended content
            String updatedContent = readFile(filename);
            System.out.println("Updated file content: " + updatedContent);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
