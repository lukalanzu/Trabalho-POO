/**
 * Exception thrown when an order is empty and an operation requiring items in the order is attempted.
 * Extends CoffeeShopException.
 */
public class EmptyOrderException extends CoffeeShopException {
    /**
     * Constructs an EmptyOrderException with the default message "Empty order".
     */
    public EmptyOrderException() {
        super("Empty order");
    }
}