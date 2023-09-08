package app;

import console.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public void run() throws SQLException, IOException, ClassNotFoundException {

        Console authorConsole = new AboutConsole();
        Console mainConsole = new MainConsole();
        Console empConsole = new EmployeeConsole();
        Console invConsole = new InventoryConsole();

        EXIT:
        while (true){
            mainConsole.displayMainMenu();
            int menu = mainConsole.selectMenu();
            switch (menu) {
                case 1:
                    empConsole.displayMainMenu();
                    break;
                case 2:
                    invConsole.displayMainMenu();
                    break;
                case 3:
                    authorConsole.displayMainMenu();
                    break;
                case 0:
                    break EXIT;
                default:
                    System.out.println("Enter 0 - 3");
                    break;
            }
        }
    }
}
