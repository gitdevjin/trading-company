package console;

import button.EmployeeButton;
import button.InventoryButton;
import utility.Tool;

import java.io.IOException;
import java.sql.SQLException;

public class InventoryConsole extends Console {
    public void displayMainMenu() throws IOException, SQLException, ClassNotFoundException {
        EXIT:
        while (true) {
            screen();
            int select = selectMenu();
            switch (select) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    displayByWarehouse();
                    break;
                case 3:
                    displayByKeyword();
                    System.out.println("search by product keyword");
                    break;
                case 4:
                    System.out.println("have not decided");
                    break;
                case 0:
                    System.out.println();
                    System.out.println();
                    break EXIT;
            }
        }
    }
    /******************************************************************************************/

    private void displayByWarehouse() throws SQLException, ClassNotFoundException, IOException {
        InvenWarehouseConsole invenWareConsole = new InvenWarehouseConsole();

        boolean iter = true;
        EXIT:
        while(iter){
            invenWareConsole.displayMainMenu();
            int select = selectMenu();
            if (select >0 && select <= 9) {
                invenWareConsole.displayByWarehouse(select);
            } else if (select == 0){
                System.out.println("going back to previous page");
                iter = false;
            }
        }
    }

    private void displayByKeyword() throws IOException, SQLException, ClassNotFoundException {
        Tool tools = new Tool();
        InventoryButton button = new InventoryButton();
        System.out.println("Enter Product Keyword");
        String keyword = tools.promptWord();
        button.printInventoryByKeyword(keyword);
        EXIT:
        while(true){
            int select = selectMenu();
            switch (select){
                case 1:
                    button.printInventoryByKeyword(keyword);
                    break;
                case 2:
                    button.movePrevPage();
                    button.printInventoryByKeyword(keyword);
                    break;
                case 3:
                    button.moveNextPage();
                    button.printInventoryByKeyword(keyword);
                    break;
                case 4:
                    button.showDetail();
                    break;
                case 0:
                    System.out.println();
                    System.out.println();
                    break EXIT;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private void screen() {
        System.out.println("*--------------------------------------*");
        System.out.println("*                                      *");
        System.out.println("*         Inventory Management         *");
        System.out.println("*                                      *");
        System.out.println("*--------------------------------------*");
        System.out.println();
        System.out.println("========================================");
        System.out.println("1. Display Inventory Items");
        System.out.println("2. Display Items by Warehouse");
        System.out.println("3. Search by Product Keywords");
        System.out.println("4. Haven't Decided");
        System.out.println("0. Go Back");
        System.out.println();
        System.out.println("========================================");
    }

    private void displayInventory() throws IOException, SQLException, ClassNotFoundException {
        InventoryButton button = new InventoryButton();
        button.printInventory();
        EXIT:
        while(true){
            int select = selectMenu();
            switch (select){
                case 1:
                    button.printInventory();
                    break;
                case 2:
                    button.movePrevPage();
                    button.printInventory();
                    break;
                case 3:
                    button.moveNextPage();
                    button.printInventory();
                    break;
                case 4:
                    button.showDetail();
                    break;
                case 0:
                    System.out.println("going back to previous page");
                    break EXIT;
            }
        }
    }

    public int selectMenu() throws IOException {
        Tool tools = new Tool();
        int result;
        while (true) {
            result = tools.promptNumber();
            if(result >= 0 && result <= 9){
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        return result;
    }

}
