/**
 * Exception thrown when an invalid payment type is selected during checkout.
 * Extends CoffeeShopException.
 */
public class InvalidPaymentTypeException extends CoffeeShopException {
    /**
     * Constructs an InvalidPaymentTypeException with a specific payment type.
     *
     * @param paymentType The invalid payment type that caused the exception.
     */
    public InvalidPaymentTypeException(String paymentType) {
        super("Invalid payment type: " + paymentType);
    }
}