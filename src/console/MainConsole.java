package console;

import utility.Tool;

import java.io.IOException;
import java.util.Scanner;

public class MainConsole extends Console {
    public void displayMainMenu() {
        System.out.println("========================================");
        System.out.println("===                                  ===");
        System.out.println("===     ************************     ===");
        System.out.println("***     * Mega Trading Company *     ***");
        System.out.println("===     ************************     ===");
        System.out.println("===                                  ===");
        System.out.println("========================================");
        System.out.println();
        System.out.println("========================================");
        System.out.println("1. Manage Employee");
        System.out.println("2. Manage Inventory");
        System.out.println("3. About Author");
        System.out.println("0. EXIT");
        System.out.println();
        System.out.println("========================================");
    };

    public int selectMenu() throws IOException {
        Tool tools = new Tool();
        int result;
        while(true) {
            result = tools.promptNumber();
            if(result >= 0 && result <= 3){
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        return result;
    }


}
