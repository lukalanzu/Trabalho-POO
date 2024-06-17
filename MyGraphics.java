import javax.swing.*;
import java.awt.*;

/**
 * Custom component for drawing a logo with a pink star and text "Coffee Shop".
 * Extends JComponent.
 */
public class MyGraphics extends JComponent {

    /**
     * Constructs a MyGraphics object, setting the preferred size of the logo to 100x100 pixels.
     */
    public MyGraphics() {
        // Setting the size of the logo
        setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Paints the logo component with a white background, a pink star, and the text "Coffee Shop".
     *
     * @param g The Graphics context used for painting.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background color (optional)
        g.setColor(Color.white);
        g.fillRect(0, 0, 100, 100);

        // Drawing the pink star
        Color brown = new Color(210, 105, 30);
        g.setColor(brown);
        int[] xPoints = {50, 61, 98, 68, 79, 50, 21, 32, 2, 39};
        int[] yPoints = {15, 35, 35, 54, 90, 70, 90, 54, 35, 35};
        g.fillPolygon(xPoints, yPoints, 10);

        // Adding the text "Coffee Shop"
        g.setColor(Color.black); // Black color for the text
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Font settings
        g.drawString("Coffee Shop", 15, 95); // Position the text
    }
}