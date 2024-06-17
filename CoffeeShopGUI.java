import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GUI class for a coffee shop management application.
 * It handles the creation of the user interface, event handling, and business logic for ordering food and drinks,
 * managing inventory, and generating sales reports.
 */
public class CoffeeShopGUI extends JFrame implements ActionListener {
    static ProductPanel[] food;
    static ProductPanel[] drink;
    static JTextArea order;
    JTextArea inventoryTextArea;
    JComboBox<String> paymentTypeComboBox;
    JButton submit, cancel, showInventory, showSalesReport, dailyReport, weeklyReport, monthlyReport;
    JPanel foodPanel, drinkPanel, itemPanel, buttonPanel, logoPanel, inventoryPanel;
    static List<Sale> salesHistory = new ArrayList<>();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static int[] stockQuantity = new int[19];
    Inventory inventory;

    /**
     * Constructs a CoffeeShopGUI object.
     * Initializes the inventory text area and inventory object.
     */
    public CoffeeShopGUI() {
        inventoryTextArea = new JTextArea(10, 20);
        inventoryTextArea.setBackground(new Color(245,222,179));
        inventory = new Inventory(inventoryTextArea, stockQuantity, food, drink);
    }

    /**
     * Creates and sets up the GUI components.
     */
    public void createGUI() {

        setLocation(500, 300);
        setTitle("Java Cafe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Arrays.fill(stockQuantity, 20);

        logoPanel = new JPanel();
        Color beige = new Color(245,222,179);
        Color lightBeige = new Color(255,248,220);
        Color cuteBrown = new Color(160, 82, 45); // Define a brown tone
        logoPanel.setBackground(lightBeige);
        logoPanel.add(new MyGraphics());

        foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(5, 2));
        String[] foodItems = {"Cake", "Cookie", "Cheese Bread", "Caesar Salad", "Sandwich", "Brownie", "Waffle with Syrup", "Fruit Salad", "Scrambled Eggs", "Muffin"};
        double[] foodPrices = {3.50, 1.25, 2.00, 4.50, 5.00, 2.50, 3.75, 4.00, 3.00, 2.25};
        food = new ProductPanel[foodItems.length];


        for (int i = 0; i < foodItems.length; i++) {
            food[i] = new ProductPanel(foodItems[i], foodPrices[i], food, drink, order);
            foodPanel.add(food[i]);
        }

        drinkPanel = new JPanel();
        drinkPanel.setLayout(new GridLayout(9, 1));

        String[] drinkItems = {"Espresso", "Iced Latte", "Milk Tea", "Orange Juice", "Matcha", "Bubble Tea", "Sparkling Water", "Mocha", "Soda"};
        double[] drinkPrices = {2.00, 3.50, 4.00, 3.00, 3.75, 4.25, 1.50, 3.25, 1.75};
        drink = new ProductPanel[drinkItems.length];

        for (int i = 0; i < drinkItems.length; i++) {
            drink[i] = new ProductPanel(drinkItems[i], drinkPrices[i], food, drink, order);
            drinkPanel.add(drink[i]);
        }

        itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 2));
        itemPanel.setBackground(lightBeige);
        itemPanel.add(foodPanel);
        itemPanel.add(drinkPanel);

        Color brown = new Color(210, 105, 30);

        submit = new JButton("Submit");
        submit.setBackground(brown);
        submit.setForeground(Color.BLACK);

        cancel = new JButton("Cancel");
        cancel.setBackground(brown);
        cancel.setForeground(Color.BLACK);

        showInventory = new JButton("Show Inventory");
        showInventory.setBackground(brown);
        showInventory.setForeground(Color.BLACK);

        showSalesReport = new JButton("Show Sales Report");
        showSalesReport.setBackground(brown);
        showSalesReport.setForeground(Color.BLACK);

        dailyReport = new JButton("Daily Report");
        dailyReport.setBackground(brown);
        dailyReport.setForeground(Color.BLACK);

        weeklyReport = new JButton("Weekly Report");
        weeklyReport.setBackground(brown);
        weeklyReport.setForeground(Color.BLACK);

        monthlyReport = new JButton("Monthly Report");
        monthlyReport.setBackground(brown);
        monthlyReport.setForeground(Color.BLACK);

        submit.setOpaque(true);
        submit.setBorderPainted(false);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        showInventory.setOpaque(true);
        showInventory.setBorderPainted(false);
        showSalesReport.setOpaque(true);
        showSalesReport.setBorderPainted(false);
        dailyReport.setOpaque(true);
        dailyReport.setBorderPainted(false);
        weeklyReport.setOpaque(true);
        weeklyReport.setBorderPainted(false);
        monthlyReport.setOpaque(true);
        monthlyReport.setBorderPainted(false);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(lightBeige);
        buttonPanel.add(submit);
        buttonPanel.add(cancel);
        buttonPanel.add(showInventory);
        buttonPanel.add(showSalesReport);
        buttonPanel.add(dailyReport);
        buttonPanel.add(weeklyReport);
        buttonPanel.add(monthlyReport);

        PaymentSelected renderer = new PaymentSelected(brown);
        String[] paymentMethods = {"Select payment method", "Money", "Credit Card", "Debit Card"};
        paymentTypeComboBox = new JComboBox<>(paymentMethods);
        paymentTypeComboBox.setRenderer(renderer);
        paymentTypeComboBox.setBackground(brown);
        paymentTypeComboBox.setForeground(Color.BLACK);
        paymentTypeComboBox.setOpaque(true);
        paymentTypeComboBox.setBorder(null);
        buttonPanel.add(paymentTypeComboBox);

        buttonPanel.revalidate();
        buttonPanel.repaint();

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(beige);

        submit.addActionListener(this);
        cancel.addActionListener(this);
        showInventory.addActionListener(this);
        showSalesReport.addActionListener(this);
        dailyReport.addActionListener(this);
        weeklyReport.addActionListener(this);
        monthlyReport.addActionListener(this);

        order = new JTextArea(10, 20);

        JScrollPane scrollPanePanel = new JScrollPane(itemPanel);
        JScrollPane scrollPaneOrder = new JScrollPane(order);
        order.setBackground(lightBeige);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPanePanel, BorderLayout.CENTER);
        contentPane.add(scrollPaneOrder, BorderLayout.EAST);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(logoPanel, BorderLayout.WEST);

        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(1, 1));
        JScrollPane scrollPaneInventory = new JScrollPane(inventoryTextArea);
        inventoryPanel.add(scrollPaneInventory);
        contentPane.add(inventoryPanel, BorderLayout.NORTH);
        inventoryPanel.setVisible(false);

        itemPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        pack();
    }

    /**
     * Handles action events triggered by the buttons and combo box in the GUI.
     *
     * @param e the ActionEvent triggered by user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == submit) {
                if (isOrderEmpty()) {
                    throw new EmptyOrderException();
                }
                String paymentType = (String) paymentTypeComboBox.getSelectedItem();
                if (!Arrays.asList("Money", "Credit Card", "Debit Card").contains(paymentType)) {
                    throw new InvalidPaymentTypeException(paymentType);
                }

                updateSalesReport();
                showOrder();
                showReceiptWindow();
                inventory.updateInventory(food, drink);
                paymentTypeComboBox.setSelectedIndex(0);
            }
            if (e.getSource() == cancel) {
                resetOrder();
            }
            if (e.getSource() == showInventory) {
                inventoryPanel.setVisible(true);
                inventory.updateInventory(food, drink);
            }
            if (e.getSource() == showSalesReport){
                generateSalesReportToFile("SalesReport");
            }
            if (e.getSource() == dailyReport) {
                showDetailedReport("Daily", 1);
                //generateSalesReportToFile("DailySalesReport.txt");
            }
            if (e.getSource() == weeklyReport) {
                showDetailedReport("Weekly", 7);
                //generateSalesReportToFile("WeeklySalesReport.txt");
            }
            if (e.getSource() == monthlyReport) {
                showDetailedReport("Monthly", 30);
                //generateSalesReportToFile("MonthlySalesReport.txt");
            }

        } catch (EmptyOrderException ex) {
            JOptionPane.showMessageDialog(this, "Your order is empty. Add items to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (OutOfStockException ex) {
            JOptionPane.showMessageDialog(this, "Out of stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidPaymentTypeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sales history is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the current order is empty.
     *
     * @return true if the order is empty, false otherwise.
     */
    public boolean isOrderEmpty() {
        for (ProductPanel foodItem : food) {
            if (foodItem.quantity > 0) {
                return false;
            }
        }
        for (ProductPanel drinkItem : drink) {
            if (drinkItem.quantity > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resets the current order, clearing all quantities and resetting the order text.
     */
    public void resetOrder() {
        for (ProductPanel foodItem : food) {
            foodItem.quantity = 0;
            foodItem.quantityLabel.setText("0");
        }

        for (ProductPanel drinkItem : drink) {
            drinkItem.quantity = 0;
            drinkItem.quantityLabel.setText("0");
        }

        order.setText("");
        paymentTypeComboBox.setSelectedIndex(0);
    }

    /**
     * Displays the current order and updates the inventory accordingly.
     *
     * @throws OutOfStockException if any item in the order is out of stock.
     */
    public void showOrder() throws OutOfStockException {
        StringBuilder orderText = new StringBuilder("Order:\n");
        double total = 0;

        for (ProductPanel foodItem : food) {
            if (foodItem.quantity > 0) {
                if (stockQuantity[Arrays.asList(food).indexOf(foodItem)] >= foodItem.quantity) {
                    orderText.append(foodItem.nameLabel.getText()).append(": ").append(foodItem.quantity).append(" x $").append(foodItem.price).append(" = $").append(foodItem.quantity * foodItem.price).append("\n");
                    total += foodItem.quantity * foodItem.price;
                    stockQuantity[Arrays.asList(food).indexOf(foodItem)] -= foodItem.quantity;
                } else {
                    throw new OutOfStockException(foodItem.nameLabel.getText());
                }
            }
        }

        for (ProductPanel drinkItem : drink) {
            if (drinkItem.quantity > 0) {
                if (stockQuantity[Arrays.asList(drink).indexOf(drinkItem) + food.length] >= drinkItem.quantity) {
                    orderText.append(drinkItem.nameLabel.getText()).append(": ").append(drinkItem.quantity).append(" x $").append(drinkItem.price).append(" = $").append(drinkItem.quantity * drinkItem.price).append("\n");
                    total += drinkItem.quantity * drinkItem.price;
                    stockQuantity[Arrays.asList(drink).indexOf(drinkItem) + food.length] -= drinkItem.quantity;
                } else {
                    throw new OutOfStockException(drinkItem.nameLabel.getText());
                }
            }
        }

        for (ProductPanel foodItem : food) {
            foodItem.quantity = 0;
            foodItem.quantityLabel.setText("0");
        }

        for (ProductPanel drinkItem : drink) {
            drinkItem.quantity = 0;
            drinkItem.quantityLabel.setText("0");
        }

        orderText.append("\nTotal whith taxes: $").append(total+total*0.05);

        order.setText(orderText.toString());

        inventory.updateInventory(food, drink );
    }

    /**
     * Updates the sales report by adding the current order details to the sales history.
     */
    void updateSalesReport() {
        StringBuilder saleDetails = new StringBuilder("Order:\n");
        Date now = new Date();

        for (ProductPanel foodItem : food) {
            if (foodItem.quantity > 0) {
                saleDetails.append(foodItem.nameLabel.getText()).append(": ").append(foodItem.quantity).append(" x $").append(foodItem.price).append("\n");
            }
        }

        for (ProductPanel drinkItem : drink) {
            if (drinkItem.quantity > 0) {
                saleDetails.append(drinkItem.nameLabel.getText()).append(": ").append(drinkItem.quantity).append(" x $").append(drinkItem.price).append("\n");
            }
        }

        Sale sale = new Sale(saleDetails.toString(), now);
        salesHistory.add(sale);
    }

    /**
     * Generates a sales report and writes it to a file.
     *
     * @param fileName the name of the file to write the sales report to.
     */
    private void generateSalesReportToFile(String fileName) {
        StringBuilder reportText = new StringBuilder("Sales Report:\n");

        for (Sale sale : salesHistory) {
            reportText.append(dateFormatter.format(sale.getDate())).append("\n").append(sale.getOrderDetails()).append("\n\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTextArea reportArea = new JTextArea(reportText.toString());
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JOptionPane.showMessageDialog(null, scrollPane, "Sales Report", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a new window with the receipt of the current order.
     */
    private void showReceiptWindow() {
        JFrame receiptFrame = new JFrame("Receipt");
        JTextArea receiptText = new JTextArea();
        receiptText.setEditable(false);
        receiptText.setText(order.getText());

        receiptFrame.add(new JScrollPane(receiptText));
        receiptFrame.setSize(300, 400);
        receiptFrame.setLocationRelativeTo(null);
        receiptFrame.setVisible(true);
    }

    /**
     * Shows a detailed sales report for a specified period.
     *
     * @param period the period of the report (e.g., "Daily", "Weekly", "Monthly").
     * @param days the number of days in the period.
     */
    private void showDetailedReport(String period, int days) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date startDate = calendar.getTime();

        StringBuilder reportText = new StringBuilder(period + " Sales Report:\n");
        double totalSales = 0;
        int numTransactions = 0;
        Map<String, Integer> itemSales = new HashMap<>();

        for (Sale sale : salesHistory) {
            if (sale.getDate().after(startDate) && sale.getDate().before(now)) {
                reportText.append(dateFormatter.format(sale.getDate())).append("\n").append(sale.getOrderDetails()).append("\n\n");
                totalSales += sale.calculateTotalSale();
                numTransactions++;
                sale.updateItemSales(itemSales);
            }
        }

        String bestSeller = Sale.findBestSeller(itemSales);

        reportText.append("\nTotal Sales: $").append(totalSales).append("\n");
        reportText.append("Number of Transactions: ").append(numTransactions).append("\n");
        reportText.append("Best-selling Product: ").append(bestSeller).append("\n");

        String fileName = period + "_SalesReport.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTextArea reportArea = new JTextArea(reportText.toString());
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        JOptionPane.showMessageDialog(null, scrollPane, period + " Sales Report", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * The main method to launch the CoffeeShopGUI application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        CoffeeShopGUI frame = new CoffeeShopGUI();
        frame.createGUI();
    }
}
