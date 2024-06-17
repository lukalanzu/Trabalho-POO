import javax.swing.JTextArea;

/**
 * Represents the inventory management for food and drink products in a coffee shop.
 */
public class Inventory {
    private JTextArea inventoryTextArea;
    private int[] stock;

    /**
     * Constructs an Inventory object with the specified JTextArea for displaying inventory,
     * initial stock array, and arrays of food and drink ProductPanel objects.
     *
     * @param inventoryTextArea JTextArea where the inventory information is displayed.
     * @param initialStock      Initial stock levels for both food and drink products.
     * @param food              Array of ProductPanel objects representing food products.
     * @param drink             Array of ProductPanel objects representing drink products.
     */
    public Inventory(JTextArea inventoryTextArea, int[] initialStock, ProductPanel[] food, ProductPanel[] drink) {
        this.inventoryTextArea = inventoryTextArea;
        this.stock = initialStock;
    }

    /**
     * Updates the inventory display based on current stock levels of food and drink products.
     * Also indicates products needing replenishment when stock falls below 4 units.
     *
     * @param food  Array of ProductPanel objects representing food products.
     * @param drink Array of ProductPanel objects representing drink products.
     */
    public void updateInventory(ProductPanel[] food, ProductPanel[] drink) {
        StringBuilder inventoryText = new StringBuilder("Inventory:\n");

        // Update food inventory
        for (int i = 0; i < food.length; i++) {
            if (stock[i] < 4) {
                inventoryText.append(food[i].nameLabel.getText()).append(": ").append(stock[i]).append("             [!] REPOSIÇÃO [!]").append("\n");
            } else {
                inventoryText.append(food[i].nameLabel.getText()).append(": ").append(stock[i]).append("\n");
            }
        }

        // Update drink inventory
        for (int i = 0; i < drink.length; i++) {
            if (stock[i + food.length] < 4) {
                inventoryText.append(drink[i].nameLabel.getText()).append(": ").append(stock[i + food.length]).append("             [!] REPOSIÇÃO [!]").append("\n");
            } else {
                inventoryText.append(drink[i].nameLabel.getText()).append(": ").append(stock[i + food.length]).append("\n");
            }
        }

        inventoryTextArea.setText(inventoryText.toString());
    }

}
