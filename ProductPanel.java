import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

/**
 * Represents a panel displaying product information and controls for ordering in a coffee shop GUI.
 */
public class ProductPanel extends JPanel implements ActionListener {
    JLabel nameLabel;
    JButton plusButton, minusButton;
    JLabel quantityLabel;
    int quantity;
    double price;
    ProductPanel[] food;
    ProductPanel[] drink;
    JTextArea order;

    /**
     * Constructs a ProductPanel with specified name, price, food and drink arrays, and order JTextArea.
     * Initializes GUI components and sets up action listeners for buttons.
     *
     * @param name   The name of the product.
     * @param price  The price of the product.
     * @param food   Array of food ProductPanels.
     * @param drink  Array of drink ProductPanels.
     * @param order  JTextArea displaying the current order.
     */
    public ProductPanel(String name, double price, ProductPanel[] food, ProductPanel[] drink, JTextArea order) {
        this.nameLabel = new JLabel(name + " ($" + price + ")");
        this.plusButton = new JButton("+");
        this.minusButton = new JButton("-");
        this.quantityLabel = new JLabel("0");
        this.quantity = 0;
        this.price = price;
        this.food = food;
        this.drink = drink;
        this.order = order;
        Color brown = new Color(210, 105, 30);
        plusButton.setBackground(brown);
        plusButton.setForeground(Color.BLACK);
        minusButton.setBackground(brown);
        minusButton.setForeground(Color.BLACK);

        plusButton.setOpaque(true);
        plusButton.setBorderPainted(false);
        minusButton.setOpaque(true);
        minusButton.setBorderPainted(false);

        this.plusButton.addActionListener(this);
        this.minusButton.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align items to the left
        this.add(nameLabel);
        this.add(minusButton);
        this.add(quantityLabel);
        this.add(plusButton);
    }

    /**
     * Handles button click events for plus and minus buttons.
     * Updates the quantity and calls updateOrder() to refresh the order display.
     *
     * @param e ActionEvent generated by the button click.
     */
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
            updateOrder();
        }
    }

    /**
     * Updates the order display based on the type of product (food or drink).
     */
    private void updateOrder() {
        if (Arrays.asList(food).contains(this)) {
            updateFoodOrder();
        }
        else if (Arrays.asList(drink).contains(this)) {
            updateDrinkOrder();
        }
    }

    /**
     * Updates the food order displayed in the CoffeeShopGUI.
     * Constructs a text representation of the current food order and sets it in the order JTextArea.
     */
    public static void updateFoodOrder() {
        StringBuilder orderText = new StringBuilder("Food order:\n");
        for (ProductPanel foodItem : CoffeeShopGUI.food) {
            if (foodItem.quantity > 0) {
                orderText.append(foodItem.nameLabel.getText()).append(" x").append(foodItem.quantity).append("\n");
            }
        }
        CoffeeShopGUI.order.setText(orderText.toString());
    }

    /**
     * Updates the drink order displayed in the CoffeeShopGUI.
     * Constructs a text representation of the current drink order and sets it in the order JTextArea.
     */
    public static void updateDrinkOrder() {
        StringBuilder orderText = new StringBuilder("Drink order:\n");
        for (ProductPanel drinkItem : CoffeeShopGUI.drink) {
            if (drinkItem.quantity > 0) {
                orderText.append(drinkItem.nameLabel.getText()).append(" x").append(drinkItem.quantity).append("\n");
            }
        }
        CoffeeShopGUI.order.setText(orderText.toString());
    }

}