package utility;

import java.io.IOException;
import java.util.Scanner;

public class Tool {
    public int promptNumber() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.printf(">>> ");

        int result;
        try {
            String userInput = scan.nextLine();
            result = Integer.parseInt(userInput);

        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    public String promptWord() {
        return promptWord(1);
    }
    public String promptWord(int option) {
        Scanner scan = new Scanner(System.in);
        System.out.printf(">>> ");
        String result = "";
        boolean bad = true;
        while (bad) {
            String userInput = scan.nextLine();
            String[] words = userInput.split("\\s+");
            if (words.length > 0 || option == 0) {
                result = words[0];
                bad = false;
            } else {
                System.out.println("Empty String is not allowed");
            }
        }
        return result;
    }

}
