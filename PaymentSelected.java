import javax.swing.*;
import java.awt.*;

/**
 * Custom renderer for handling the appearance of payment options in a selection box.
 * Extends DefaultListCellRenderer.
 */
public class PaymentSelected extends DefaultListCellRenderer {
    private Color brown = new Color(139, 69, 19);;

    /**
     * Constructs a PaymentSelected renderer with a specified color for selected items.
     *
     * @param selectedColor The color to be used when an item is selected.
     */
    public PaymentSelected(Color selectedColor) {
        this.brown = selectedColor;
    }

    /**
     * Returns the component used for rendering an item in a JList.
     *
     * @param list          The JList that is the parent of the cell.
     * @param value         The value to assign to the cell.
     * @param index         The cell index.
     * @param isSelected    True if the specified cell is selected.
     * @param cellHasFocus  True if the specified cell has the focus.
     * @return              The component used for rendering the cell.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (isSelected) {
            c.setBackground(brown);
            c.setForeground(Color.WHITE);
        } else {
            c.setBackground(brown);
            c.setForeground(Color.BLACK);
        }
        return c;
    }
}
