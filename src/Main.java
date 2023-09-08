import app.App;
import console.Console;
import console.EmployeeConsole;
import console.InventoryConsole;
import console.MainConsole;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        App mainApp = new App();
        mainApp.run();

    }
}