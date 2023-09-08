package query;

import table.Employee;
import table.Inventory;
import table.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryQuery extends Query{
    public InventoryQuery() {
        super();
    }

    public List<Inventory> getInventories(int page) throws ClassNotFoundException, SQLException {

        int start = 1 + (page - 1) * 20;
        int end = page * 20;

        String sql = "SELECT * FROM (SELECT ROWNUM NUM, a.* FROM (SELECT * FROM (SELECT e.product_id, p.product_name, w.warehouse_name, e.quantity" +
                " FROM inventories e join products p on e.product_id = p.product_id" +
                " join warehouses w on e.warehouse_id = w.warehouse_id order by e.product_id)) a) WHERE NUM BETWEEN ? AND ?";
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, start);
        st.setInt(2, end);
        ResultSet rs = st.executeQuery();

        List<Inventory> inventoryList = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("PRODUCT_ID");
            String pName = rs.getString("PRODUCT_NAME");
            String wName = rs.getString("WAREHOUSE_NAME");
            int qty = rs.getInt("QUANTITY");

            Inventory inv = new Inventory(id, pName, wName, qty);
            inventoryList.add(inv);
        }

        rs.close();
        st.close();
        conn.close();

        return inventoryList;
    }
    public int getCount() throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(product_id) COUNT FROM inventories";
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next())
            count = rs.getInt("count");

        rs.close();
        st.close();
        conn.close();

        return count;
    }

    public int getCountByWarehouse(int warehouseId) throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(product_id) COUNT FROM inventories WHERE warehouse_id = " + warehouseId;
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next())
            count = rs.getInt("count");

        rs.close();
        st.close();
        conn.close();

        return count;
    }

    public int getCountByKeyword(String keyword) throws ClassNotFoundException, SQLException {
        int count = 0;
        String sql = "SELECT COUNT(i.product_id) COUNT FROM inventories i join products p on i.product_id = p.product_id WHERE Lower(p.product_name) LIKE " +
                "'%" +keyword.toLowerCase()+ "%'";
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next())
            count = rs.getInt("count");

        rs.close();
        st.close();
        conn.close();

        return count;
    }

    public List<Inventory> getInventoriesByKeyword(int page, String keyword) throws ClassNotFoundException, SQLException {

        int start = 1 + (page - 1) * 20;
        int end = page * 20;

        String sql = "SELECT * FROM (SELECT ROWNUM NUM, a.* FROM (SELECT * FROM (SELECT e.product_id, p.product_name, w.warehouse_name, e.quantity" +
                " FROM inventories e join products p on e.product_id = p.product_id" +
                " join warehouses w on e.warehouse_id = w.warehouse_id WHERE Lower(p.product_name) LIKE ? order by e.product_id)) a) WHERE NUM BETWEEN ? AND ?";
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "%"+keyword.toLowerCase()+"%");
        st.setInt(2, start);
        st.setInt(3, end);
        ResultSet rs = st.executeQuery();

        List<Inventory> inventoryList = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("PRODUCT_ID");
            String pName = rs.getString("PRODUCT_NAME");
            String wName = rs.getString("WAREHOUSE_NAME");
            int qty = rs.getInt("QUANTITY");

            Inventory inv = new Inventory(id, pName, wName, qty);
            inventoryList.add(inv);
        }
        return inventoryList;
    }

    public List<Inventory> getInventoriesByWareHouse(int page, int warehouseId) throws ClassNotFoundException, SQLException {

        int start = 1 + (page - 1) * 20;
        int end = page * 20;

        String sql = "SELECT * FROM (SELECT ROWNUM NUM, a.* FROM (SELECT * FROM (SELECT e.product_id, p.product_name, w.warehouse_name, e.quantity" +
                " FROM inventories e join products p on e.product_id = p.product_id" +
                " join warehouses w on e.warehouse_id = w.warehouse_id WHERE e.warehouse_id = ? order by e.product_id)) a) WHERE NUM BETWEEN ? AND ?";
        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, warehouseId);
        st.setInt(2, start);
        st.setInt(3, end);
        ResultSet rs = st.executeQuery();

        List<Inventory> inventoryList = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("PRODUCT_ID");
            String pName = rs.getString("PRODUCT_NAME");
            String wName = rs.getString("WAREHOUSE_NAME");
            int qty = rs.getInt("QUANTITY");

            Inventory inv = new Inventory(id, pName, wName, qty);
            inventoryList.add(inv);
        }

        rs.close();
        st.close();
        conn.close();

        return inventoryList;
    }

    public Product findProduct(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * From products WHERE product_id = ?";

        Class.forName(super.getDriver());
        Connection conn = DriverManager.getConnection(super.getUrl(), super.getUid(), super.getPwd());
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        Product prod = null;

        if(rs.next()) {
            int prodId = rs.getInt("PRODUCT_ID");
            String pName = rs.getString("PRODUCT_NAME");
            String desc = rs.getString("DESCRIPTION");
            double price = rs.getDouble("LIST_PRICE");
            prod = new Product(prodId, pName, desc, price);
        }

        return prod;
    }
}
