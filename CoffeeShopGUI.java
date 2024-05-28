import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class CoffeeShopGUI extends JFrame implements ActionListener {
    static ProductPanel[] food;
    static ProductPanel[] drink;
    static JTextArea order;
    JTextArea inventory;
    JButton submit, cancel;
    JPanel foodPanel, drinkPanel, itemPanel, buttonPanel, logoPanel;

    public static class MyGraphics extends JComponent {

        // Constructor
        public MyGraphics() {
            // Setting the size of the logo
            setPreferredSize(new Dimension(100, 100));
        }

        // Painting the logo
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Background color (optional)
            g.setColor(Color.white);
            g.fillRect(0, 0, 100, 100);

            // Drawing the pink star
            g.setColor(Color.pink);
            int[] xPoints = {50, 61, 98, 68, 79, 50, 21, 32, 2, 39};
            int[] yPoints = {15, 35, 35, 54, 90, 70, 90, 54, 35, 35};
            g.fillPolygon(xPoints, yPoints, 10);

            // Adding the text "Coffee Shop"
            g.setColor(Color.black); // Black color for the text
            g.setFont(new Font("Arial", Font.BOLD, 14)); // Font settings
            g.drawString("Coffee Shop", 15, 95); // Position the text
        }
    }

    public static class ProductPanel extends JPanel implements ActionListener {
        JLabel nameLabel;
        JButton plusButton, minusButton;
        JLabel quantityLabel;
        int quantity;

        public ProductPanel(String name) {
            this.nameLabel = new JLabel(name);
            this.plusButton = new JButton("+");
            this.minusButton = new JButton("-");
            this.quantityLabel = new JLabel("0");
            this.quantity = 0;

            this.plusButton.addActionListener(this);
            this.minusButton.addActionListener(this);

            this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align items to the left
            this.add(nameLabel);
            this.add(minusButton);
            this.add(quantityLabel);
            this.add(plusButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == plusButton || e.getSource() == minusButton) {
                if (e.getSource() == plusButton) {
                    quantity++;
                } else if (e.getSource() == minusButton) {
                    if (quantity > 0) {
                        quantity--;
                    }
                }
                quantityLabel.setText(String.valueOf(quantity));

                // Atualiza o pedido
                updateOrder();
            }
        }

        // Método para atualizar o pedido
        private void updateOrder() {
            // Atualiza o pedido de comida
            if (Arrays.asList(food).contains(this)) {
                updateFoodOrder();
            }
            // Atualiza o pedido de bebida
            else if (Arrays.asList(drink).contains(this)) {
                updateDrinkOrder();
            }
        }
    }

    public void createGUI() {
        // Setting up the Frame
        setLocation(500, 300);
        setTitle("Java Cafe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Adding the logo to a JPanel
        logoPanel = new JPanel();
        logoPanel.add(new MyGraphics());

        // Creating the foods Panel
        foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(5, 2)); // 3 columns, 6 rows
        String[] foodItems = {"Cake", "Cookie", "Cheese Bread", "Caesar Salad", "Sandwich", "Brownie", "Waffle with Syrup", "Fruit Salad", "Scrambled Eggs", "Muffin"};
        food = new ProductPanel[foodItems.length];

        for (int i = 0; i < foodItems.length; i++) {
            food[i] = new ProductPanel(foodItems[i]);
            foodPanel.add(food[i]);
        }

        // Creating the drinks Panel
        drinkPanel = new JPanel();
        drinkPanel.setLayout(new GridLayout(9, 1)); // 3 columns, 6 rows
        String[] drinkItems = {"Espresso", "Iced Latte", "Milk Tea", "Orange Juice", "Matcha", "Bubble Tea", "Sparkling Water", "Mocha", "Soda"};
        drink = new ProductPanel[drinkItems.length];

        for (int i = 0; i < drinkItems.length; i++) {
            drink[i] = new ProductPanel(drinkItems[i]);
            drinkPanel.add(drink[i]);
        }

        // Including both Panels into another Panel to place them into the center of the Frame without overwriting
        itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 2)); // 2 columns, 1 row
        itemPanel.add(foodPanel);
        itemPanel.add(drinkPanel);

        // Creating the buttons
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        // Including both Buttons into a Panel to place them into the bottom of the Frame without overwriting
        buttonPanel = new JPanel();
        buttonPanel.add(submit);
        buttonPanel.add(cancel);
        buttonPanel.setLayout(new FlowLayout());

        // Adding ActionListeners to the Buttons
        submit.addActionListener(this);
        cancel.addActionListener(this);

        // Creating a textArea for displaying the order
        order = new JTextArea(10, 20);
        inventory = new JTextArea(10, 20);

        // Adding a scroll bar to the itemPanel and the textArea
        JScrollPane scrollPanePanel = new JScrollPane(itemPanel);
        JScrollPane scrollPaneOrder = new JScrollPane(order);

        // Creating the main Frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPanePanel, BorderLayout.CENTER);
        contentPane.add(scrollPaneOrder, BorderLayout.EAST);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(logoPanel, BorderLayout.WEST);

        // Adding inventory panel to the NORTH
        JScrollPane scrollPaneInventory = new JScrollPane(createInventoryPanel(foodItems, drinkItems));
        contentPane.add(scrollPaneInventory, BorderLayout.NORTH);

        // Adding borders to the Panels
        itemPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        pack();
    }

    private JPanel createInventoryPanel(String[] foodItems, String[] drinkItems) {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(0, 4)); // Multiple rows, 4 columns
        inventoryPanel.add(new JLabel("Inventory:"));

        // Adding food items
        for (String item : foodItems) {
            JLabel label = new JLabel(item + ": 20 un");
            inventoryPanel.add(label);
        }

        // Adding drink items
        for (String item : drinkItems) {
            JLabel label = new JLabel(item + ": 20 un");
            inventoryPanel.add(label);
        }

        return inventoryPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // Se o botão de "Submit" for clicado, o pedido será mostrado na área de texto.
            showOrder();
        }
    }

    // Método para mostrar o pedido na área de texto
    private void showOrder() {
        StringBuilder orderText = new StringBuilder("Order:\n");

        // Adiciona os itens de comida ao pedido
        for (ProductPanel foodItem : food) {
            if (foodItem.quantity > 0) {
                orderText.append(foodItem.nameLabel.getText()).append(" x").append(foodItem.quantity).append("\n");
            }
        }

        // Adiciona os itens de bebida ao pedido
        for (ProductPanel drinkItem : drink) {
            if (drinkItem.quantity > 0) {
                orderText.append(drinkItem.nameLabel.getText()).append(" x").append(drinkItem.quantity).append("\n");
            }
        }

        // Define o texto do pedido na área de texto
        order.setText(orderText.toString());
    }

    // Método para atualizar o pedido de comida
    public static void updateFoodOrder() {
        StringBuilder orderText = new StringBuilder("Food order:\n");
        for (ProductPanel foodItem : food) {
            if (foodItem.quantity > 0) {
                orderText.append(foodItem.nameLabel.getText()).append(" x").append(foodItem.quantity).append("\n");
            }
        }
        order.setText(orderText.toString());
    }

    // Método para atualizar o pedido de bebida
    public static void updateDrinkOrder() {
        StringBuilder orderText = new StringBuilder("Drink order:\n");
        for (ProductPanel drinkItem : drink) {
            if (drinkItem.quantity > 0) {
                orderText.append(drinkItem.nameLabel.getText()).append(" x").append(drinkItem.quantity).append("\n");
            }
        }
        order.setText(orderText.toString());
    }

    public static void main(String[] args) {
        CoffeeShopGUI GUI = new CoffeeShopGUI();
        GUI.createGUI();
    }
}
