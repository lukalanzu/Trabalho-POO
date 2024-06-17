import java.util.Date;
import java.util.Map;
import java.util.Collections;

/**
 * Class representing a sale transaction.
 */
public class Sale {
    String orderDetails;
    Date date;

    /**
     * Constructs a Sale object with specified order details and date.
     *
     * @param orderDetails Details of the order.
     * @param date Date of the sale.
     */
    public Sale(String orderDetails, Date date) {
        this.orderDetails = orderDetails;
        this.date = date;
    }

    /**
     * Gets the details of the order.
     *
     * @return The order details.
     */
    public String getOrderDetails() {
        return orderDetails;
    }

    /**
     * Gets the date of the sale.
     *
     * @return The date of the sale.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Calculates the total sale amount based on the order details.
     *
     * @return The total sale amount.
     */
    public double calculateTotalSale() {
        double total = 0;
        String[] lines = orderDetails.split("\n");

        for (String line : lines) {
            if (line.contains("$")) {
                String[] parts = line.split("\\$");
                total += Double.parseDouble(parts[2]);
            }
        }

        return total;
    }

    /**
     * Updates the item sales map with quantities from the order details.
     *
     * @param itemSales A map where the key is the item name and the value is the quantity sold.
     */
    public void updateItemSales(Map<String, Integer> itemSales) {
        String[] lines = orderDetails.split("\n");
        String[] line = lines;
        for (int i = 1; i< line.length; i++) {
            System.out.println(lines[i]);
            if (line[i].contains(":")) {
                String itemName = line[i].split("\\(")[0].trim();
                System.out.println(itemName);
                int quantity = Integer.parseInt((line[i].split(":")[1].split(" x ")[0]).trim());
                itemSales.put(itemName, itemSales.getOrDefault(itemName, 0) + quantity);
            }
        }
    }

    /**
     * Finds the best-selling item based on the item sales map.
     *
     * @param itemSales A map where the key is the item name and the value is the quantity sold.
     * @return The name of the best-selling item.
     */
    public static String findBestSeller(Map<String, Integer> itemSales) {
        System.out.println(itemSales);
        System.out.println(Collections.max(itemSales.entrySet(), Map.Entry.comparingByValue()).toString());
        return Collections.max(itemSales.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}