import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the CoffeeShopGUI class.
 */
class TestCoffeeShop {

    /**
     * Sets up the initial configuration before each test.
     * Initializes an instance of CoffeeShopGUI and creates the GUI.
     */
    private CoffeeShopGUI coffeeShopGUI;

    @BeforeEach
    void setUp() {
        coffeeShopGUI = new CoffeeShopGUI();
        coffeeShopGUI.createGUI();
    }

    /**
     * Test to verify if the order is empty initially.
     * Simulates adding an item to the order and checks if the order is no longer empty.
     */
    @Test
    void testIsOrderEmpty() {
        assertTrue(coffeeShopGUI.isOrderEmpty(), "Order should be empty initially");

        // Simulating adding an item to the order
        CoffeeShopGUI.food[0].quantity = 1;
        assertFalse(coffeeShopGUI.isOrderEmpty(), "Order should not be empty after adding an item");
        System.out.println("TEST 1 SUCCESS");
    }

    /**
     * Test to verify if the order is reset correctly.
     * Adds items to the order, resets the order, and checks if all fields are reset correctly.
     */
    @Test
    void testResetOrder() {
        CoffeeShopGUI.food[0].quantity = 1;
        CoffeeShopGUI.drink[0].quantity = 1;
        coffeeShopGUI.resetOrder();

        assertTrue(coffeeShopGUI.isOrderEmpty(), "Order should be empty after reset");
        assertEquals(0, CoffeeShopGUI.food[0].quantity, "Food item quantity should be reset to 0");
        assertEquals(0, CoffeeShopGUI.drink[0].quantity, "Drink item quantity should be reset to 0");
        assertEquals(Optional.ofNullable(""), Optional.ofNullable(CoffeeShopGUI.order.getText()), "Order text should be empty after reset");
        assertEquals(0, coffeeShopGUI.paymentTypeComboBox.getSelectedIndex(), "Payment type should be reset to default");
        System.out.println("TEST 2 SUCCESS");
    }

    /**
     * Test to verify the display of the order with valid quantities.
     * Sets a valid quantity for an item and checks if the order is displayed correctly and if the stock is reduced.
     */
    @Test
    void testShowOrderValid() {
        CoffeeShopGUI.food[0].quantity = 2; // Setting a valid quantity

        assertDoesNotThrow(() -> {
            coffeeShopGUI.showOrder();
        }, "showOrder should not throw any exception for valid quantities");

        assertEquals(18, CoffeeShopGUI.stockQuantity[0], "Stock should be reduced correctly");
        assertTrue(CoffeeShopGUI.order.getText().contains(CoffeeShopGUI.food[0].nameLabel.getText()), "Order text should contain the food item");
        System.out.println("TEST 3 SUCCESS");
    }

    /**
     * Test to verify the display of the order with both food and drink items.
     * Adds food and drink to the order and checks if both items are displayed correctly and if the stock is reduced.
     */
    @Test
    void testShowOrderWithFoodAndDrink() {
        CoffeeShopGUI.food[0].quantity = 1;
        CoffeeShopGUI.drink[0].quantity = 1;

        assertDoesNotThrow(() -> coffeeShopGUI.showOrder(), "showOrder should not throw any exception for valid quantities");

        assertEquals(19, CoffeeShopGUI.stockQuantity[0], "Food stock should be reduced correctly");
        assertEquals(19, CoffeeShopGUI.stockQuantity[10], "Drink stock should be reduced correctly");
        assertTrue(CoffeeShopGUI.order.getText().contains(CoffeeShopGUI.food[0].nameLabel.getText()), "Order text should contain the food item");
        assertTrue(CoffeeShopGUI.order.getText().contains(CoffeeShopGUI.drink[0].nameLabel.getText()), "Order text should contain the drink item");
        System.out.println("TEST 4 SUCCESS");
    }


    /**
     * Test to verify if the OutOfStockException is thrown correctly.
     * Sets a quantity greater than the available stock and checks if the exception is thrown.
     */
    @Test
    void testShowOrderThrowsOutOfStockException() {
        CoffeeShopGUI.food[0].quantity = 21; // Setting quantity more than stock

        Exception exception = assertThrows(OutOfStockException.class, (ThrowingRunnable) () -> {
            coffeeShopGUI.showOrder();
        });
        assertTrue(exception.getMessage().contains(CoffeeShopGUI.food[0].nameLabel.getText()), "Exception message should contain the name of the out of stock item");
        System.out.println("TEST 5 SUCCESS");
    }
}