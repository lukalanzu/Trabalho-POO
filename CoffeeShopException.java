/**
 * Custom exception class for CoffeeShop application.
 * This exception is used to indicate general exceptions related to CoffeeShop operations.
 */
public class CoffeeShopException extends Exception {

    /**
     * Constructs a CoffeeShopException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public CoffeeShopException(String message) {
        super(message);
    }
}
