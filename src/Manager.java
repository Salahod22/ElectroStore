import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manager extends JFrame implements ActionListener {
    private JPanel p, pPhones, pButtons, pCustomer, plproducts, plcustmer, pTotal, psouth, imagePanel;
    private JLabel lTotal, lBenefit, lSalesTotal, limage, llTotal,
            lcustmer, lproducts, llBenefit, llSalesTotal;
    private JTable produtsTable, ordersTable;
    private JButton bAdd, bRemove;

    private ImageIcon image1;

    public Manager() {

        Color redback = new Color(230, 36, 1);
        Color bgray = new Color(74, 82, 97);

        lcustmer = new JLabel("Orders");
        lcustmer.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 28));
        lcustmer.setForeground(Color.WHITE);

        plcustmer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        plcustmer.add(lcustmer);
        plcustmer.setBackground(redback);

        lproducts = new JLabel("Products");
        lproducts.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 28));
        lproducts.setForeground(Color.WHITE);

        plproducts = new JPanel(new FlowLayout(FlowLayout.CENTER));
        plproducts.add(lproducts);
        plproducts.setBackground(redback);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return ImageIcon.class;
                } else {
                    return Object.class;
                }
            }
        };
        model.addColumn("Image");
        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Price");
        model.addColumn("Sell Price");
        model.addColumn("Quantity");

        produtsTable = new JTable(model);
        produtsTable.setBackground(Color.LIGHT_GRAY);
        produtsTable.setForeground(Color.WHITE);
        produtsTable.setGridColor(Color.black);
        produtsTable.setRowHeight(70);
        produtsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        produtsTable.setForeground(Color.BLACK);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        produtsTable.setDefaultRenderer(Object.class, renderer);

        bAdd = new JButton("Add");
        bAdd.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 28));

        bRemove = new JButton("Remove");
        bRemove.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 28));

        bAdd.setBackground(bgray);
        bAdd.setForeground(Color.WHITE);
        bRemove.setBackground(bgray);
        bRemove.setForeground(Color.WHITE);

        pButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 13));
        pButtons.setBackground(redback);
        pButtons.add(bAdd);
        pButtons.add(bRemove);

        pPhones = new JPanel(new BorderLayout());
        pPhones.setBackground(redback);
        pPhones.add(plproducts, BorderLayout.NORTH);
        JScrollPane scrollPane1 = new JScrollPane(produtsTable);
        scrollPane1.setPreferredSize(new Dimension(690, 400));
        pPhones.add(scrollPane1, BorderLayout.CENTER);

        DefaultTableModel customerModel = new DefaultTableModel();
        customerModel.addColumn("Order id");
        customerModel.addColumn("Product id");
        customerModel.addColumn("Name");
        customerModel.addColumn("Price");
        customerModel.addColumn("Phone Number");
        customerModel.addColumn("Email");
        customerModel.addColumn("Address");
        customerModel.addColumn("Quantity");

        ordersTable = new JTable(customerModel);
        ordersTable.setBackground(Color.LIGHT_GRAY);
        ordersTable.setForeground(Color.WHITE);
        ordersTable.setGridColor(Color.black);
        ordersTable.setRowHeight(70);
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordersTable.setForeground(Color.BLACK);

        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);
        ordersTable.setDefaultRenderer(Object.class, renderer1);

        pCustomer = new JPanel(new BorderLayout());
        pCustomer.setBackground(redback);
        pCustomer.add(plcustmer, BorderLayout.NORTH);
        JScrollPane customerScrollPane = new JScrollPane(ordersTable);
        customerScrollPane.setPreferredSize(new Dimension(690, 400));
        pCustomer.add(customerScrollPane, BorderLayout.CENTER);

        lTotal = new JLabel("Total");
        lTotal.setForeground(Color.WHITE);
        lTotal.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));

        lBenefit = new JLabel("Benifit :");
        lBenefit.setForeground(Color.WHITE);
        lBenefit.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));

        lSalesTotal = new JLabel("Total sales :");
        lSalesTotal.setForeground(Color.WHITE);
        lSalesTotal.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));

        llTotal = new JLabel();
        llTotal.setForeground(Color.GREEN);
        llTotal.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));

        llBenefit = new JLabel();
        llBenefit.setForeground(Color.GREEN);
        llBenefit.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));

        llSalesTotal = new JLabel();
        llSalesTotal.setForeground(Color.GREEN);
        llSalesTotal.setFont(new Font("Rockwell Extra Bold", Font.ROMAN_BASELINE, 20));


        pTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        pTotal.setBackground(redback);
        pTotal.add(lTotal);
        pTotal.add(llTotal);
        pTotal.add(lBenefit);
        pTotal.add(llBenefit);
        pTotal.add(lSalesTotal);
        pTotal.add(llSalesTotal);

        psouth = new JPanel(new BorderLayout());
        psouth.setBackground(redback);
        psouth.add(pTotal, BorderLayout.EAST);
        psouth.add(pButtons, BorderLayout.WEST);

        image1 = new ImageIcon("C:\\Users\\John\\eclipse-workspace\\Project1\\src\\ele.png");
        limage = new JLabel(image1);

        imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(redback);
        imagePanel.add(limage);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        p = new JPanel(new BorderLayout());
        p.setBackground(redback);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        p.add(imagePanel, BorderLayout.NORTH);

        p.add(pPhones, BorderLayout.WEST);
        p.add(pCustomer, BorderLayout.EAST);
        p.add(psouth, BorderLayout.SOUTH);
        add(p);

        setTitle("Manager Interface");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        bAdd.addActionListener(this);
        bRemove.addActionListener(this);

        displayProducts();
        displayOrders();
        double totalOrdersellPrice = calculateTotalOrdersellPrice();
        llTotal.setText(String.valueOf(totalOrdersellPrice));

        updateBenefitField();
        countOrdersAndDisplay();

    }

    private void countOrdersAndDisplay() {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        int totalQuantity = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            totalQuantity += (int) model.getValueAt(i, model.findColumn("Quantity"));
        }
        llSalesTotal.setText(Integer.toString(totalQuantity));
    }

    private double calculateTotalOrdersellPrice() {
        double selltotal = 0.0;
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double sellprice = Double.parseDouble(model.getValueAt(i, 3).toString());
            int quantity = Integer.parseInt(model.getValueAt(i, 7).toString());
            selltotal += sellprice * quantity;
        }
        return selltotal;
    }

    private double calculateTotalOrderbuyPrice() {
        double buytotal = 0.0;
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int productId = Integer.parseInt(model.getValueAt(i, 1).toString());
            int quantity = Integer.parseInt(model.getValueAt(i, 7).toString());
            double buyPrice = getbuyPrice(productId);
            buytotal += buyPrice * quantity;
        }

        return buytotal;
    }

    private double getbuyPrice(int productId) {
        double buyPrice = 0.0;

        String query = "SELECT buy_price FROM products WHERE product_id = ?";

        try (Connection conn = Base.connexionBD();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                buyPrice = resultSet.getDouble("buy_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyPrice;
    }

    private void updateBenefitField() {
        double totalsell = Double.parseDouble(llTotal.getText());
        double totalbuyprice = calculateTotalOrderbuyPrice();
        double benefit = totalsell - totalbuyprice;
        llBenefit.setText(String.valueOf(benefit));
    }

    private List<Order> fetchOrders() {
        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders";
        try {
            Connection conn = Base.connexionBD();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                double total_price = resultSet.getDouble("total_price");

                Order order = new Order(orderId, productId, name, address, phoneNumber, email, price, quantity, total_price);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private void displayOrders() {
        List<Order> orders = fetchOrders();
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);

        for (Order order : orders) {
            model.addRow(new Object[]{order.getOrderId(), order.getProductId(), order.getName(),
                    order.getPrice(), order.getPhoneNumber(), order.getEmail(), order.getAddress(), order.getQuantity()});
        }
    }


    public static void main(String[] args) {

        new Manager();

    }

    public void addproduct() {
        String imagePath = JOptionPane.showInputDialog("Enter Image Path:");
        String prodId = JOptionPane.showInputDialog("Enter Product ID:");
        String name = JOptionPane.showInputDialog("Enter Product Name:");
        String description = JOptionPane.showInputDialog("Enter Product Description:");
        String buy_price = JOptionPane.showInputDialog("Enter Product Price:");
        String sell_price = JOptionPane.showInputDialog("Enter Product Sell Price:");
        String quantity = JOptionPane.showInputDialog("Enter Product Quantity:");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electro", "root", "");
            if (connection != null) {
                String query = "INSERT INTO products (product_id, name, category, buy_price, sell_price, quantity, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, prodId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, description);
                preparedStatement.setString(4, buy_price);
                preparedStatement.setString(5, sell_price);
                preparedStatement.setString(6, quantity);
                preparedStatement.setString(7, imagePath);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Product added successfully!");
                    displayProducts();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add product!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: Failed to connect to the database.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }


    private List<Product> fetchProducts() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products ";
        try (Connection conn = Base.connexionBD();
             PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                double buy_price = resultSet.getDouble("buy_price");
                double sell_price = resultSet.getDouble("sell_price");
                int quantity = resultSet.getInt("quantity");
                String imagePath = resultSet.getString("image_path");

                Product product = new Product(product_id, name, category, buy_price, sell_price, quantity, imagePath);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private void displayProducts() {
        List<Product> products = fetchProducts();
        DefaultTableModel model = (DefaultTableModel) produtsTable.getModel();
        model.setRowCount(0);

        for (Product product : products) {
            ImageIcon imageIcon = new ImageIcon(product.getImagePath());
            Image image = imageIcon.getImage().getScaledInstance(80, 61, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(image);
            model.addRow(new Object[]{scaledImageIcon, product.getId(), product.getName(), product.getCategory(), product.getBuyPrice(), product.getSellPrice(), product.getQuantity()});
        }
    }

    public void removeProduct() {
        int selectedRow = produtsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to remove.");
            return;
        }

        String productId = produtsTable.getValueAt(selectedRow, 1).toString();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/electro", "root", "");

            if (connection != null) {

                String query = "DELETE FROM products WHERE product_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, productId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Product removed successfully!");

                    DefaultTableModel model = (DefaultTableModel) produtsTable.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found or failed to remove!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: Failed to connect to the database.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAdd) {
            addproduct();
        } else if (e.getSource() == bRemove) {
            removeProduct();
        }
    }
}
