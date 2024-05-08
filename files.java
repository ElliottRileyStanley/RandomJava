import java.io.FileInputStream;
import java.io.IOException;

public class files {
    public static void main(String[] args) {
        // Specify the file path
        String filePath = "C:\\Users\\270000056\\Desktop\\Coding\\Java\\Random\\sodoku.java"; // Change this to your file path

        try {
            // Open the file
            FileInputStream inputStream = new FileInputStream(filePath);

            // Read and print the binary data
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                // Convert the byte to binary string representation
                String binaryString = Integer.toBinaryString(byteRead);

                // Ensure it's 8 bits long
                while (binaryString.length() < 8) {
                    binaryString = "0" + binaryString;
                }

                // Print the binary data
                System.out.println(binaryString);
            }

            // Close the file
            inputStream.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}