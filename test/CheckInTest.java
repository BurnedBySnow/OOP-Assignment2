import Enums.Category;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;


public class CheckInTest {
    CheckIn checkIn = new CheckIn();

    @Test
    public void isActiveMemberTest() {
        assert (checkIn.isActiveMember(LocalDate.parse("2023-02-04")));
        assert (checkIn.isActiveMember(LocalDate.parse("2022-12-25")));
        assert (checkIn.isActiveMember(LocalDate.now().minusYears(1).plusDays(1)));
        assert (!checkIn.isActiveMember(LocalDate.now().minusYears(1)));
        assert (!checkIn.isActiveMember(LocalDate.parse("2022-05-26")));
        assert (!checkIn.isActiveMember(LocalDate.parse("2021-06-16")));
    }

    @Test
    public void checkMemberStatusTest(){
        CheckIn checkIn = new CheckIn();
        String input = "Alhambra Aromes";
        String input1 = "8204021234";
        String input2 = "Max Maxxon";

        assert (checkIn.checkMemberStatus(input) == Category.Active);
        assert (checkIn.checkMemberStatus(input1) == Category.Previous);
        assert (checkIn.checkMemberStatus(input2) == Category.Never);
    }
}
