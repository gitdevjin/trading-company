package table;

public class Inventory {
    private int productId;
    private String productName;
    private String warehouse;
    private int qty;

    public Inventory() {}

    public Inventory(int productId, String productName, String warehouse, int qty) {
        this.productId = productId;
        this.productName = productName;
        this.warehouse = warehouse;
        this.qty = qty;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
