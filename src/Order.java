
public class Order {
    private int orderId;
    private String name;
    private int productId;
    private double price;
    private String phoneNumber;
    private String email;
    private double total_price;
    private String address;
    private int quantity;

    
    public Order(int orderId, int productId, String name, String address,
                 String phoneNumber, String email, double price , int quantity, double total_price) {
        this.orderId = orderId;
        this.name = name;      
        this.productId = productId;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setTotale_price(double totale_price) {
    	this.total_price = totale_price;
    }
    
    public double getTotale_price() {
    	return total_price;
    }
}
