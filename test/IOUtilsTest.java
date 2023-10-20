import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class IOUtilsTest {
    String inFile = "test/files/data.txt";
    String outFile = "test/files/forPT.txt";
    @Test
    public void getCustomerTest(){
        Customer customer = new Customer("Alhambra Aromes", "7703021234", LocalDate.parse("2023-07-01"));

        assert (IOUtils.getCustomerFromFile("7703021234", inFile).getName().equals(customer.getName()));
        assert (IOUtils.getCustomerFromFile("7703021234", inFile).getPersonalNr().equals(customer.getPersonalNr()));
        assert (IOUtils.getCustomerFromFile("7703021234", inFile).getDate().isEqual(customer.getDate()));
        assert (IOUtils.getCustomerFromFile("8204021235", inFile) == null);
    }

    public final String getLastLine(String file) {
        String line = "";
        try(BufferedReader br = new BufferedReader(
                new FileReader(file));){
            String temp;
            while((temp = br.readLine()) != null) if(!temp.isEmpty()) line = temp;
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return line;
    }

    public final int countLines(String file) {
        int lines = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file));) {
            while(br.readLine() != null) lines++;
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Test
    public void writeToFileTest(){
        int lines = countLines(outFile);

        String text = "Alhambra Aromes, " + LocalDate.now();

        IOUtils.writeToFile(outFile, text);

        assert (getLastLine(outFile).equals(text));
        assert (countLines(outFile) == lines + 1);
    }
}
