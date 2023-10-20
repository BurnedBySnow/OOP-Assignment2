import Enums.Category;

import java.time.LocalDate;
import java.time.Period;

public class CheckIn {
    public boolean isActiveMember(LocalDate paidDate) {
        LocalDate currentDate = LocalDate.now();
        return (Period.between(paidDate, currentDate).toTotalMonths() < 12);
    }

    public Category checkMemberStatus(String input) {
        Customer customer = IOUtils.getCustomerFromFile(input, "src/files/data.txt");

        if (customer == null) return Category.Never; // Person not in list

        if (isActiveMember(customer.getDate())) {
            IOUtils.writeToFile("src/files/forPT.txt", customer.getName() + ", " + LocalDate.now());
            //Person paid less than 1 year ago
            return Category.Active;
        } else {
            // More than 1 year than last paid
            return Category.Previous;
        }
    }
}
