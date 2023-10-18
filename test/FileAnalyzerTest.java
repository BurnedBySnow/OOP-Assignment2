import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;


public class FileAnalyzerTest {
    FileAnalyzer fileAnalyzer = new FileAnalyzer();

    String inFile = "test/files/data.txt";
    String outFile = "test/files/forPT.txt";

    @Test
    public void isActiveMemberTest() {
        assert (fileAnalyzer.isActiveMember(LocalDate.parse("2023-02-04")));
        assert (fileAnalyzer.isActiveMember(LocalDate.parse("2022-12-25")));
        assert (!fileAnalyzer.isActiveMember(LocalDate.parse("2022-05-26")));
        assert (!fileAnalyzer.isActiveMember(LocalDate.parse("2021-06-16")));
    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Test
    public void writeToFileTest(){
        int lines = countLines(outFile);

        IOUtils.writeToFile(outFile, "Alhambra Aromes, " + LocalDate.now());

        assert (getLastLine(outFile).equals("Alhambra Aromes, " + LocalDate.now()));
        assert (countLines(outFile) == lines + 1);
    }
}
