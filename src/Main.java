import Enums.Category;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CheckIn checkIn = new CheckIn();

        while (true) {
            String input = JOptionPane.showInputDialog("Enter name or personal nr");
            if(input == null) break;

            Category res = checkIn.checkMemberStatus(input);

            if(res == Category.Active) {
                JOptionPane.showMessageDialog(null, "Welcome!");
                break;
            }
            if (res == Category.Previous) {
                JOptionPane.showMessageDialog(null, "Membership has run out!");
                break;
            }
            if(res == Category.Never){
                JOptionPane.showMessageDialog(null, "Not found in member list!");
            }
        }
    }
}