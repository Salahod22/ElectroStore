import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends JFrame implements ActionListener {

    private JPanel p, p1, p1a, p1b, p1c, p2;
    private JTextField searchTF;
    private JButton prevButton, nextButton;
    private int currentPage = 1;

    public Customer() {
        p = new JPanel();
        p.setLayout(new BorderLayout());

        p1 = new JPanel(new GridLayout(1, 3));
        p1.setBackground(new Color(0x4A5261));
        p1.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        p1a = new JPanel();
        p1a.setBackground(new Color(0x4A5261));
        ImageIcon logo = new ImageIcon(getClass().getResource("1.png"));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentPage = 1; 
                displayProducts(); 
            }
        });
        p1a.add(logoLabel);
        p1a.add(Box.createHorizontalStrut(100));
        p1.add(p1a);

        //Categories
        JButton categoriesButton = new JButton("Categories");
        categoriesButton.setBackground(new Color(0x4A5261));
        categoriesButton.setForeground(Color.WHITE);
        categoriesButton.setUI(new BasicButtonUI());
        categoriesButton.setBorder(null);
        categoriesButton.setContentAreaFilled(false);
        categoriesButton.setFont(categoriesButton.getFont().deriveFont(Font.BOLD, 17));

        JPopupMenu popupMenu = new JPopupMenu();
        
        String[] categoryNames = {"Telephone", "TV", "Ordinateur", "Electromenager"};

        for (String categoryName : categoryNames) {
            JMenuItem menuItem = new JMenuItem(categoryName);
            menuItem.setBackground(Color.GRAY);
            menuItem.setForeground(Color.WHITE); 
            Font font = menuItem.getFont(); 
            menuItem.setFont(new Font(font.getName(), Font.BOLD, 14));
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCategory = ((JMenuItem) e.getSource()).getText();
                    
                    if (selectedCategory.equals("Telephone")) {	
                    	searchCategory("tel");
                    } else if (selectedCategory.equals("TV")) {
                    	searchCategory("tv");
                    } else if (selectedCategory.equals("Ordinateur")) {
                    	searchCategory("ord");
                    } else if (selectedCategory.equals("Electromenager")) {
                    	searchCategory("electro");
                    }	
                }
            });
            popupMenu.add(menuItem);
        }
        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	popupMenu.setPreferredSize(new Dimension(120, 180));
            	popupMenu.setBackground(Color.GRAY);
                popupMenu.show(categoriesButton, 0, categoriesButton.getHeight());
                popupMenu.show(categoriesButton, 0, categoriesButton.getHeight());
            }
        });

        p1b = new JPanel();
        p1b.setBackground(new Color(0x4A5261));
        p1b.add(categoriesButton);
        p1.add(p1b);
        
        // Search Bar
        p1c = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1c.setBackground(new Color(0x4A5261));

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("3.png"));
        Image scaledImage = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledSearchIcon = new ImageIcon(scaledImage);
        JLabel searchLabel = new JLabel(scaledSearchIcon);
        searchLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        p1c.add(searchLabel);

        searchTF = new JTextField();
        searchTF.setPreferredSize(new Dimension(150, 25)); 
        searchTF.setForeground(Color.BLACK);
        searchTF.setText("");
        searchTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchTF.getText().trim(); 
               if (!searchText.isEmpty()) { 
                    searchProducts(searchText);
                } else {                  
                    displayProducts();
                }
            }
        });
        p1c.add(searchTF);
        p1.add(p1c);
        p.add(p1, BorderLayout.NORTH);

        p2 = new JPanel(new GridLayout(2, 3));
        p2.setBackground(Color.RED);
        p.add(p2, BorderLayout.CENTER);
        
        prevButton = new JButton("Previous Page");
        prevButton.setBackground(new Color(0x4A5261)); 
        prevButton.setForeground(Color.WHITE); 
        prevButton.setContentAreaFilled(true); 
        prevButton.setFocusPainted(false); 
        prevButton.addActionListener(this);

        nextButton = new JButton("Next Page");
        nextButton.setBackground(new Color(0x4A5261)); 
        nextButton.setForeground(Color.WHITE); 
        nextButton.setContentAreaFilled(true); 
        nextButton.setFocusPainted(false); 
        nextButton.addActionListener(this);

        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p3.setBackground(Color.red);

        p3.add(prevButton);
        p3.add(nextButton);
        
        p.add(p3, BorderLayout.SOUTH);

        add(p);
        setTitle("Electroplanet");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayProducts();  
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM Products";

        try (Connection conn = Base.connexionBD();
             PreparedStatement statement = conn.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                double buy_price = resultSet.getDouble("buy_price");
                double sell_price = resultSet.getDouble("sell_price");
                int quantity = resultSet.getInt("quantity");
                String imagePath = resultSet.getString("image_path");

                Product product = new Product(productId, name, category, buy_price, sell_price, quantity, imagePath);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setPreferredSize(new Dimension(200, 250)); 
        productPanel.setBackground(Color.WHITE);

        // Product image
        ImageIcon imageIcon = new ImageIcon(product.getImagePath());
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        productPanel.add(imageLabel, BorderLayout.CENTER);

        // Product name
        JLabel nameLabel = new JLabel("<html><div style='text-align: center; width: 150px;'>" + product.getName() + "</div></html>");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productPanel.add(nameLabel, BorderLayout.NORTH);

        // Product price
        JLabel priceLabel = new JLabel(product.getBuyPrice() + " DH");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 17)); 
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productPanel.add(priceLabel, BorderLayout.SOUTH);
        productPanel.addMouseListener(new MouseAdapter() {
            @Override
            //Click on product
            public void mouseClicked(MouseEvent e) {
                p2.removeAll();
                p2.setLayout(new BorderLayout());
                p2.setBackground(Color.WHITE);

                JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                detailsPanel.setPreferredSize(new Dimension(500, 100));
                detailsPanel.setBackground(Color.WHITE);
                detailsPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

                ImageIcon imageIcon = new ImageIcon(product.getImagePath());
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(scaledImageIcon);
                detailsPanel.add(imageLabel);
                
                JPanel n = new JPanel();
                n.setLayout(new BoxLayout(n, BoxLayout.Y_AXIS));
                n.setBackground(Color.WHITE);
                JLabel nameLabel = new JLabel(product.getName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
                n.add(nameLabel);
                n.add(Box.createVerticalStrut(20));
                JLabel priceLabel = new JLabel(product.getBuyPrice() + " DH");
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 24));
                n.add(priceLabel);
                detailsPanel.add(n);
                
                //Buy Button                
                JButton buyButton = new JButton("Buy");
                if (product.getQuantity() == 0) {
                	JLabel outStock = new JLabel("Out Of Stock");
                	detailsPanel.add(outStock);
                	outStock.setFont(new Font("Arial", Font.BOLD, 40));
                	outStock.setForeground(Color.RED);
                	n.add(Box.createVerticalStrut(40));
                	n.add(outStock);
                } else {
	                buyButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        JTextField nameField = new JTextField(20);
	                        JTextField addressField = new JTextField(20);
	                        JTextField phoneField = new JTextField(20);
	                        JTextField emailField = new JTextField(20);
	                        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, product.getQuantity(), 1));//todo : controll quantity
	
	                        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
	                        inputPanel.add(new JLabel("Name:"));
	                        inputPanel.add(nameField);
	                        inputPanel.add(new JLabel("Address:"));
	                        inputPanel.add(addressField);
	                        inputPanel.add(new JLabel("Phone Number:"));
	                        inputPanel.add(phoneField);
	                        inputPanel.add(new JLabel("Email:"));
	                        inputPanel.add(emailField);
	                        inputPanel.add(new JLabel("Quantity:"));
	                        inputPanel.add(quantitySpinner);
	                        
	                        while (true) {
		                        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		                        if (result == JOptionPane.OK_OPTION) {
		                            String email = emailField.getText();
		                            if (!email.contains("@")) {
		                                JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
		                                continue;
		                            }
		
		                            String name = nameField.getText();
		                            String address = addressField.getText();
		                            String phone = phoneField.getText();
		                            int quantity = (int) quantitySpinner.getValue();
		
		                            buy(product, name, address, phone, email, quantity);
		                            break;
		                        } else {
		                        	break;}
		                        }
	                       
	                    }
	                });
                }
                p2.add(detailsPanel, BorderLayout.CENTER);
                p2.add(buyButton, BorderLayout.SOUTH);
                p2.revalidate();
                p2.repaint();
            }
        });
        
        return productPanel;
    }
    
    private void displayProducts() {
        p2.removeAll(); 
        p2.setLayout(new GridLayout(2, 3, 15, 15)); 
        p2.setBackground(Color.RED);

        List<Product> products = getProducts();
        
        int startIndex = (currentPage - 1) * 6;
        int endIndex = Math.min(startIndex + 6, products.size());

        for (int i = startIndex; i < endIndex; i++) {
            Product product = products.get(i);
            JPanel productPanel = createProductPanel(product);
            p2.add(productPanel);
        }	
        p2.revalidate(); 
        p2.repaint(); 
    }

    private void searchProducts(String searchText) {
        p2.removeAll(); 
        p2.setLayout(new GridLayout(2, 3, 15, 15)); 
        p2.setBackground(Color.RED);

        List<Product> products = getProducts();
        for (Product product : products) {           
            if (product.getName().toLowerCase().contains(searchText.toLowerCase())) {
                JPanel productPanel = createProductPanel(product); 
                p2.add(productPanel); 
            }
        }
        p2.revalidate(); 
        p2.repaint(); 
    }
    	
    private void searchCategory(String category) {
        p2.removeAll();
        p2.setLayout(new GridLayout(2, 3, 15, 15));
        p2.setBackground(Color.RED);	

        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                JPanel productPanel = createProductPanel(product);
                p2.add(productPanel);
            }
        }
        p2.revalidate();
        p2.repaint();
    }
    
    
    private void buy(Product product, String name, String address, String phone_number, String email, int quantity) {
        double price = product.getSellPrice();
        double totalPrice = quantity * price;

        try {
            Connection conn = Base.connexionBD();
     
            String query = "INSERT INTO orders (product_id, name, address, phone_number, email, price, quantity, total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, product.getId());
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, phone_number);
            statement.setString(5, email);
            statement.setDouble(6, price);
            statement.setInt(7, quantity);
            statement.setDouble(8, totalPrice);
            statement.executeUpdate();
            statement.close();

            query = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setInt(2, product.getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
            displayProducts();

            JOptionPane.showMessageDialog(null, "Order placed successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Customer c = new Customer();
        c.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == nextButton) {
            currentPage++;
            displayProducts();
        } else if (e.getSource() == prevButton && currentPage > 1) {
            currentPage--;
            displayProducts();
        }
    }
}
