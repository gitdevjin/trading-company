package console;

import java.util.Scanner;

public class AboutConsole extends Console {
    Scanner scan = new Scanner(System.in);
    public void displayMainMenu() {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=========================================");
        System.out.println("      Author's Info");
        System.out.println("      Name : Hyunjin Shin");
        System.out.println("      Date : 2023-09-08");
        System.out.println("      Development tools Used For the App ");
        System.out.println("      ===>> Java, Oracle, JDBC, SQL  ");
        System.out.println("=========================================");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("  Enter to go back to main menu");
        System.out.printf(">>> ");
        scan.nextLine();
    }

    public int selectMenu() {
        System.out.println("Empty");
        return 0;
    }
}
