/**
 * Exception thrown when an item is out of stock in the coffee shop inventory.
 * Extends CoffeeShopException.
 */
public class OutOfStockException extends CoffeeShopException {
    /**
     * Constructs an OutOfStockException with the specified item name that is out of stock.
     *
     * @param itemName The name of the item that is out of stock.
     */
    public OutOfStockException(String itemName) {
        super("Out of stock: " + itemName);
    }
}