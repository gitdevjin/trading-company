package button;

import query.EmployeeQuery;
import query.InventoryQuery;
import table.Employee;
import table.Inventory;
import table.Product;
import utility.Tool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class InventoryButton {
    private InventoryQuery IQuery;
    private int page;
    public InventoryButton() {
        this.IQuery = new InventoryQuery();
        this.page = 1;
    }
    public void movePrevPage() {
        if (page > 1) {
            --page;
        } else {
            System.out.println("------------------------");
            System.out.println("| It is the first page!|");
            System.out.println("------------------------");
        }
    }

    public void moveNextPage() throws SQLException, ClassNotFoundException {
        int count = IQuery.getCount();
        int lastPage = count % 10 > 0 ? count / 10 + 1 : count / 10;
        if (page < lastPage) ++page;
        else {
            System.out.println("------------------------");
            System.out.println("| It is the last page! |");
            System.out.println("------------------------");
        }
    }

    public void printInventory() throws SQLException, ClassNotFoundException {
        List<Inventory> list = IQuery.getInventories(page);
        int count = IQuery.getCount();
        System.out.println("------------------------------------------------");
        System.out.printf("<Inventories of Mega Trading Company> : Total %d \n", count);
        System.out.println("------------------------------------------------");
        System.out.printf("%4s   %-45s  %-19s %3s \n", "PID", "NAME", "WAREHOUSE", "QTY");
        for (Inventory i : list) {
            System.out.printf(
                    "%04d. %-40s   %-23s  %3d \n",
                    i.getProductId(),
                    i.getProductName(),
                    i.getWarehouse(),
                    i.getQty());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("                   %d/%d pages                 \n", page, count / 20 + 1);
        System.out.println("1. Refresh / 2. Previous / 3. Next / 4. Details ");
        System.out.println("0. GO BACK");
    }

    public void printInventoryByWarehouse(int warehouseId) throws SQLException, ClassNotFoundException {
        List<Inventory> list = IQuery.getInventoriesByWareHouse(page, warehouseId);
        int count = IQuery.getCountByWarehouse(warehouseId);
        System.out.println("------------------------------------------------");
        System.out.printf("<Inventories of Mega Trading Company> : Total %d \n", count);
        System.out.println("------------------------------------------------");
        System.out.printf("%4s   %-45s  %-19s %3s \n", "PID", "NAME", "WAREHOUSE", "QTY");
        for (Inventory i : list) {
            System.out.printf(
                    "%04d. %-40s   %-23s  %3d \n",
                    i.getProductId(),
                    i.getProductName(),
                    i.getWarehouse(),
                    i.getQty());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("                   %d/%d pages                 \n", page, count / 20 + 1);
        System.out.println("1. Refresh / 2. Previous / 3. Next / 4. Details ");
        System.out.println("0. GO BACK");
    }

    public void printInventoryByKeyword(String keyword) throws SQLException, ClassNotFoundException {
        List<Inventory> list = IQuery.getInventoriesByKeyword(page, keyword);
        int count = IQuery.getCountByKeyword(keyword);
        System.out.println("------------------------------------------------");
        System.out.printf("<Inventories of Mega Trading Company> : Total %d \n", count);
        System.out.println("------------------------------------------------");
        System.out.printf("%4s   %-45s  %-19s %3s \n", "PID", "NAME", "WAREHOUSE", "QTY");
        for (Inventory i : list) {
            System.out.printf(
                    "%04d. %-40s   %-23s  %3d \n",
                    i.getProductId(),
                    i.getProductName(),
                    i.getWarehouse(),
                    i.getQty());
        }
        System.out.println("------------------------------------------------");
        System.out.printf("                   %d/%d pages                 \n", page, count / 20 + 1);
        System.out.println("1. Refresh / 2. Previous / 3. Next / 4. Details ");
        System.out.println("0. GO BACK");
    }

    public void showDetail() throws IOException, SQLException, ClassNotFoundException {
        InventoryQuery IQuery = new InventoryQuery();
        Tool tools = new Tool();
        Product prod = null;
        System.out.println("Enter Product ID to see");
        int productId = tools.promptNumber();
        prod = IQuery.findProduct(productId);
        if (prod == null) {
            System.out.println("No employee Found");
        } else {
            System.out.println();
            System.out.println();
            System.out.println("----------------------------------------------");
            System.out.println("---------       PRODUCT DETAIL       ---------");
            System.out.println("----------------------------------------------");
            System.out.println("==============================================");
            System.out.println("   Product ID   : " +  prod.getProductId());
            System.out.println("   Product Name : " + prod.getProductName());
            System.out.println("   Description  : " + prod.getDescription());
            System.out.println("   Price        : $ " + prod.getPrice());
            System.out.println("==============================================");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println("1. List   / 2. Previous / 3. Next / 4. Details ");
            System.out.println("0. Go Back");

        }

    }
}
