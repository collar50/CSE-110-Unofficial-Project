/*
    @TODO I need to get a better handle on GridBagLayout. I also need to learn 
    more about JTable and JTextField. Ultimately, I need to 
    
    1) Make it so that data in the table cannot be over-written. 
    2) Make it so that clicking a column header sorts over that characteristic
    3) Make the add item button add an entry in the table, and update the view
    4) Format prices as a currency (is there a way to format input in JTextFields?)
    5) Add a delete item button. Make it so that you can highlight a row and 
            click a delete button to get rid of that row. 
    6) *Hook up some item database to this display.* 
 */
package budgetplanner.UI;

//---------SWING-----------
// Component stuff
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
// Centering stuff inside of Components
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//----------AWT------------
// Action Listener stuff
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// Layout stuff
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.table.TableCellRenderer;

public class ItemDatabasePanel extends Subpanel {

    // CONSTRUCTORS ------------------------------------------------------------
    public ItemDatabasePanel(UpdateableFrame frame) {
        panelName = "itemDatabasePanel";

        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpTextFields();
        setUpScrollPane(setUpTable());
        setUpButtons(frame);
    }

    // OTHER INTERFACE METHODS -------------------------------------------------
    @Override
    public void CreatePanel(UpdateableFrame frame) {
        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpTextFields();
        setUpScrollPane(setUpTable());
        setUpButtons(frame);
    }

    // SUPPORT METHODS ---------------------------------------------------------
    private void setUpGridBagConstraints() {
        gbc = new GridBagConstraints(); // Used to modify properties of cells. 
        gbc.insets = new Insets(0, 0, 0, 0); // Sets spacing between cells. 
    }

    private void setUpPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(BACK_COLOR);
    }

    private void setUpLabels() {
        // Local variable declarations
        JLabel title, namePrompt, descriptionPrompt, pricePrompt;

        // Title
        title = new JLabel("<html><center>Item<br/>Database", SwingConstants.CENTER);
        DecorateComponent(title, LABEL_COLOR, 50, this.LABEL_BORDER);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        PositionComponent(gbc, title, 0, 0, 1, 0);

        // Name Prompt
        namePrompt = new JLabel("<html><center>Name:", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(namePrompt, LABEL_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, namePrompt, 0, 1, .1, 0);

        // Price Prompt
        pricePrompt = new JLabel("<html><center>Price:", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(pricePrompt, LABEL_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, pricePrompt, 0, 2, .1, 0);

        // Description Prompt
        descriptionPrompt = new JLabel("<html><center>Description:", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(descriptionPrompt, LABEL_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, descriptionPrompt, 0, 3, .1, 0);
    }

    private void setUpTextFields() {
        // Local variable declarations
        JTextField nameInput, descriptionInput, priceInput;

        // Name Input
        nameInput = new JTextField("Name", SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(nameInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, nameInput, 1, 1, .7, 0);

        // Price Input
        priceInput = new JTextField("Price", SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(priceInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, priceInput, 1, 2, .7, 0);

        // Description Input
        descriptionInput = new JTextField("Description", SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(descriptionInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, descriptionInput, 1, 3, .7, 0);
    }

    private JTable setUpTable() {
        JTable table;

        // This stuff is what will go in the table--------------------------
        String[] columnNames = {"Name", "Price", "Description"};
        Object[][] data = {{"Bread", 2.50, "Wheat"},
        {"Milk", 2.00, "Skim"},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""},
        {"", "", ""}};
        //-----------------------------------------------------------------

        table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                if (data % 2 == 0) {
                    c.setBackground(CELL_COLOR_1);
                } else {
                    c.setBackground(CELL_COLOR_2);
                }

                return c;
            }
        };

        table.setFillsViewportHeight(true);
        table.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        DecorateComponent(table, Color.gray, 20, EMPTY_BORDER);

        return table;
    }

    private void setUpScrollPane(JTable table) {
        JScrollPane scroll;

        scroll = new JScrollPane(table);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;

        PositionComponent(gbc, scroll, 0, 5, 1, 1);
    }

    private void setUpButtons(UpdateableFrame frame) {
        class MainMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.changeView(frame.getMainMenuPanel());
            }
        }

        JButton back, addItem;
        ActionListener listener = new MainMenuListener();

        back = new JButton("Back to Main Menu");
        DecorateComponent(back, BUTTON_COLOR, 25, BUTTON_BORDER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PositionComponent(gbc, back, 0, 6, .5f, 0);
        back.addActionListener(listener);

        addItem = new JButton("Add Item");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(addItem, BUTTON_COLOR, 25, BUTTON_BORDER);
        PositionComponent(gbc, addItem, 0, 4, 0, 0);
    }
}
