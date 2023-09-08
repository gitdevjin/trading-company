package console;

import button.InventoryButton;
import utility.Tool;

import java.io.IOException;
import java.sql.SQLException;

public class InvenWarehouseConsole extends Console{

    public void displayByWarehouse(int warehouseId) throws IOException, SQLException, ClassNotFoundException {
        InventoryButton button = new InventoryButton();
        button.printInventoryByWarehouse(warehouseId);
        EXIT:
        while(true) {
            int select = selectMenu();
            switch (select){
                case 1:
                    button.printInventoryByWarehouse(warehouseId);
                    break;
                case 2:
                    button.movePrevPage();
                    button.printInventoryByWarehouse(warehouseId);
                    break;
                case 3:
                    button.moveNextPage();
                    button.printInventoryByWarehouse(warehouseId);
                    break;
                case 4:
                    button.showDetail();
                    break;
                case 0:
                    System.out.println();
                    System.out.println();
                    break EXIT;
            }
        }
    }
    public void displayMainMenu() throws IOException, SQLException, ClassNotFoundException {
        screen();
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

    private void screen(){
        System.out.println("*--------------------------------------*");
        System.out.println("*                                      *");
        System.out.println("*           Select WareHouse           *");
        System.out.println("*                                      *");
        System.out.println("*--------------------------------------*");
        System.out.println("1. Southlake, Texas    / 2. San Fransisco / 3. New Jersey ");
        System.out.println("4. Seattle, Washington / 5. Toronto       / 6. Sydney ");
        System.out.println("7. Mexico City         / 8. Beijing        /9. Bombay");
        System.out.println("0. GO BACK");
    }



}
