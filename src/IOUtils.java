import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class IOUtils {
    public static Customer getCustomerFromFile(String userInput, String filePath) {
        Path p = Paths.get(filePath);
        LocalDate date = null;
        String name = null;
        String personalNr = null;

        try (BufferedReader br = Files.newBufferedReader(p)) {
            String temp;
            String[] tempArr;

            while ((temp = br.readLine()) != null) {
                String temp2 = br.readLine();
                tempArr = temp.split(",");
                if (tempArr[1].trim().equalsIgnoreCase(userInput) || tempArr[0].equalsIgnoreCase(userInput)) {
                    personalNr = tempArr[0];
                    name = tempArr[1].trim();
                    date = LocalDate.parse(temp2);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (name == null) {
            return null;
        } else {
            return new Customer(name, personalNr, date);
        }
    }

    public static void writeToFile(String file, String text) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text);
            bw.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Could not write to file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
