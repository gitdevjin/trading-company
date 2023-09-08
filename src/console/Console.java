package console;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Console {
    public abstract void displayMainMenu() throws IOException, SQLException, ClassNotFoundException;
    public abstract int selectMenu() throws IOException;

}
