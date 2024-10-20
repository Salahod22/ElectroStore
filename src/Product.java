public class Product {
    private int product_id;
    private String name;
    private String category;
    private double buy_price;
    private double sell_price;
    private int quantity;
    private String imagePath;

    public Product(int product_id, String name, String category, double buy_price, double sell_price, int quantity, String imagePath) {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.buy_price = buy_price;
        this.sell_price = sell_price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }
    
    public int getId() {
        return product_id;
    }

    public void setId(int id) {
        this.product_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getSellPrice() {
        return sell_price;
    }

    public void setBuyPrice(double buy_price) {
        this.buy_price = buy_price;
    }

    public double getBuyPrice() {
        return buy_price;
    }

    public void setSellPrice(double sell_price) {
        this.sell_price = sell_price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    
}
