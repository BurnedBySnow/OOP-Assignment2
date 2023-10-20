import java.time.LocalDate;

public class Customer {
    private String name;
    private String personalNr;
    private LocalDate date; // when the customer latest paid

    public Customer(String name, String personalNr, LocalDate date) {
        this.name = name;
        this.personalNr = personalNr;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPersonalNr() {
        return personalNr;
    }
}
