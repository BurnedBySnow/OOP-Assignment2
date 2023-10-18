import Enums.Category;

import java.time.LocalDate;
import java.time.Period;

public class FileAnalyzer {
    public boolean isActiveMember(LocalDate paidDate) {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(paidDate, currentDate).toTotalMonths() < 12);
    }

    public Category checkMemberStatus(String input) {
        Customer customer = IOUtils.getCustomerFromFile(input, "src/files/data.txt");

        if (customer == null) return Category.Never;

        if (isActiveMember(customer.getDate())) {
            IOUtils.writeToFile("src/files/forPT.txt", customer.getName() + ", " + LocalDate.now());
            return Category.Active;
        } else {
            return Category.Previous;
        }
    }
}
