package budgetplanner.UI;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class Subpanel extends JPanel {

    // Instance Variables
    protected String panelName;
    protected GridBagConstraints gbc;

    // This is stuff that you can play around with and see instant, predictable effects. 
    private final String FONT_TYPE = "Georgia";

    protected final Color BACK_COLOR = Color.decode("#ffcc54");
    protected final Color LABEL_COLOR = Color.decode("#ff7354");
    protected final Color BUTTON_COLOR = Color.decode("#547cff");
    protected final Color BORDER_COLOR = Color.decode("#804e42");
    protected final Color FORE_COLOR = Color.white;
    protected final Color CELL_COLOR_1 = Color.DARK_GRAY;
    protected final Color CELL_COLOR_2 = Color.GRAY;

    protected final LineBorder LABEL_BORDER = new LineBorder(BORDER_COLOR, 5);
    protected final EmptyBorder EMPTY_BORDER = new EmptyBorder(15, 15, 15, 15);
    private final EmptyBorder EMPTY_BORDER_2 = new EmptyBorder(5, 10, 5, 10);
    protected final BevelBorder BEVEL_BORDER = new BevelBorder(BevelBorder.RAISED, Color.decode("#8888FF"), Color.decode("#0022DD"));
    protected final Border BUTTON_BORDER = new CompoundBorder(BEVEL_BORDER, EMPTY_BORDER_2);

    // ACCESSSORS --------------------------------------------------------------
    public String getPanelName() {
        return panelName;
    }

    // OTHER INTERFACE METHODS -------------------------------------------------
    public void CreatePanel(UpdateableFrame frame) {
        // No constructor body; we just need to make sure that the constructor
        // takes an UpdateableFrame. We will use it to update our panels on 
        // button clicks. 
    }

    // SUPPORT METHODS ---------------------------------------------------------
    /*
        Gives our components a consistent style. 
        
        Need:
        1) The component to be decorated
        2) The color of the background => BACK_COLOR is just a preset color we could use. 
        3) The size of the font
        4) The border for the component
     */
    protected void DecorateComponent(JComponent component, Color backgroundColor, int fontSize, Border border) {
        component.setBackground(backgroundColor);
        component.setForeground(FORE_COLOR);
        component.setAlignmentX(CENTER_ALIGNMENT);
        component.setBorder(border);
        component.setFont(new Font(FONT_TYPE, Font.ROMAN_BASELINE, fontSize));

        if (component.getClass() == JButton.class) {
            DecorateButton((JButton) component);
        } else if (component.getClass() == JLabel.class) {
            DecorateTitle((JLabel) component);
        }
    }

    /*
        Positions the components in our panel according to a grid bag layout. 
    
        Need 
        1) The GridBagConstraints
        2) The component to be positioned
        3) The x-position in the grid where the component is to be stored
        4) The y-position in the grid where the component is to be stored
        5) The x-weight
        6) The y-weight
     */
    protected void PositionComponent(GridBagConstraints c, JComponent currentComponent, int xPos, int yPos, double weightX, double weightY) {
        c.gridx = xPos;
        c.gridy = yPos;
        c.weightx = weightX;
        c.weighty = weightY;

        this.add(currentComponent, c);
        c.fill = GridBagConstraints.NONE;
    }

    // Gets rid of the ugly focus painting on buttons 
    private void DecorateButton(JButton button) {
        button.setFocusPainted(false);
    }

    // Gives labels solid backgrounds
    private void DecorateTitle(JLabel title) {
        title.setOpaque(true);
    }

}
