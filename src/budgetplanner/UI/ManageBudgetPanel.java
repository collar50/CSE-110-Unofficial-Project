/*
    @TODO 
        1) Set up the 'manage budgets' subpanel.
        2) Hook b2 button up to 'manage budgets' panel. 
        3) Play with colors in the Subpanel file to make something better looking. 
 */
package budgetplanner.UI;

//---------SWING-----------
// Component stuff
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
// Centering stuff inside of Components
import javax.swing.SwingConstants;

//----------AWT------------
// Action Listener stuff
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// Layout stuff
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

// Lists
public class ManageBudgetPanel extends Subpanel {

    // INSTANCE VARIABLES
    JTextField nameInput, descriptionInput;
    JFormattedTextField dateInput;

    // CONSTRUCTORS ------------------------------------------------------------
    public ManageBudgetPanel(UpdateableFrame frame) {
        panelName = "manageBudget";

        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpTextFields();

        JTable table = setUpTable();
        setUpScrollPane(table);
        setUpButtons(frame, table);
    }

    // INTERFACE METHODS -------------------------------------------------------
    @Override
    public void CreatePanel(UpdateableFrame frame) {
        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpTextFields();
        setUpScrollPane(setUpTable());
        //setUpButtons(frame);
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
        title = new JLabel("<html><center>Manage<br/>Budgets", SwingConstants.CENTER);
        DecorateComponent(title, LABEL_COLOR, 50, this.LABEL_BORDER);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        PositionComponent(gbc, title, 0, 0, 1, 0);

        // Name Prompt
        namePrompt = new JLabel("<html><center>Budget Name:", SwingConstants.CENTER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(namePrompt, LABEL_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, namePrompt, 0, 1, .1, 0);

        // Price Prompt
        pricePrompt = new JLabel("<html><center>Budget Date:", SwingConstants.CENTER);
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

        final String namePlaceholder, descriptionPlaceholder;

        // Name Input
        namePlaceholder = "Name of the Budget";
        nameInput = new JTextField(namePlaceholder, SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(nameInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, nameInput, 1, 1, .7, 0);
        AddTextFieldPlaceholder(nameInput, namePlaceholder);

        // Date Input
        MaskFormatter dateMask = null;

        try {
            dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholder("mm/dd/yyyy");
        } catch (ParseException ex) {
            Logger.getLogger(ManageBudgetPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        dateInput = new JFormattedTextField(dateMask);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(dateInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, dateInput, 1, 2, .7, 0);
        FocusUnfocusDateField(dateInput, dateMask);

        // Description Input
        descriptionPlaceholder = "Budget Description";
        descriptionInput = new JTextField(descriptionPlaceholder, SwingConstants.CENTER);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(descriptionInput, BUTTON_COLOR, 12, EMPTY_BORDER);
        PositionComponent(gbc, descriptionInput, 1, 3, .7, 0);
        AddTextFieldPlaceholder(descriptionInput, descriptionPlaceholder);
    }

    private JTable setUpTable() {
        JTable table;
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Date", "Description"}, 0);

        // This stuff is what will go in the table--------------------------        
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
            
            /*
            @Override 
            public Component prepareRenderer(){
                
            }
*/

        };

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    table.setSelectionBackground(Color.RED);
                } else {
                    c.setBackground(row % 2 == 0 ? CELL_COLOR_1 : CELL_COLOR_2);
                }

                return c;
            }
        };

        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

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

    private void setUpButtons(UpdateableFrame frame, JTable table) {
        class MainMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.changeView(frame.getMainMenuPanel());
            }
        }

        class AddBudgetListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean isNameFilled = !nameInput.getText().equals("");
                boolean isDescriptionFilled = !descriptionInput.getText().equals("");
                boolean isDateFilled = validateDate(dateInput);

                if (isNameFilled && isDescriptionFilled && isDateFilled) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{nameInput.getText(), dateInput.getText(), descriptionInput.getText()});
                }
            }
        }

        class DeleteBudgetListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int[] rows = table.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    model.removeRow(rows[i] - i);
                }
            }
        }

        JButton back, toBudget, addBudget, deleteBudget;
        ActionListener backToMain = new MainMenuListener();
        ActionListener addToTable = new AddBudgetListener();
        ActionListener deleteFromTable = new DeleteBudgetListener();

        back = new JButton("Back");
        DecorateComponent(back, BUTTON_COLOR, 25, BUTTON_BORDER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PositionComponent(gbc, back, 0, 6, 0, 0);
        back.addActionListener(backToMain);

        toBudget = new JButton("Go to Budget");
        DecorateComponent(toBudget, BUTTON_COLOR, 25, BUTTON_BORDER);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PositionComponent(gbc, toBudget, 1, 6, 0, 0);
        //toBudget.addActionListener(backToMain);

        deleteBudget = new JButton("Delete Budget");
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(deleteBudget, BUTTON_COLOR, 25, BUTTON_BORDER);
        PositionComponent(gbc, deleteBudget, 0, 4, .5, 0);
        deleteBudget.addActionListener(deleteFromTable);

        addBudget = new JButton("Add Budget");
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DecorateComponent(addBudget, BUTTON_COLOR, 25, BUTTON_BORDER);
        PositionComponent(gbc, addBudget, 1, 4, .5, 0);
        addBudget.addActionListener(addToTable);

    }

    private void AddTextFieldPlaceholder(JTextField textField, String placeholder) {
        Font placeholderFont = new Font(textField.getFont().getName(), Font.ITALIC, textField.getFont().getSize());
        Font filledFont = new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize());

        textField.setForeground(Color.LIGHT_GRAY);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(placeholderFont);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setFont(filledFont);
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setFont(placeholderFont);
                    textField.setForeground(Color.LIGHT_GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private void FocusUnfocusDateField(JFormattedTextField textField, MaskFormatter dateMask) {
        Font placeholderFont = new Font(textField.getFont().getName(), Font.ITALIC, textField.getFont().getSize());
        Font filledFont = new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize());

        textField.setForeground(Color.LIGHT_GRAY);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(placeholderFont);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dateMask.setPlaceholder("  /  /    ");
                textField.setFont(filledFont);
                textField.setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {

                // if the date entered is invalid, clear it out, grey it out 
                // and reset the placeholder.
                if (!validateDate(textField)) {
                    textField.setValue(null);
                    dateMask.setPlaceholder("mm/dd/yyyy");
                    textField.setFont(placeholderFont);
                    textField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private boolean validateDate(JTextField dateInput) {
        boolean isMonthVerified = true;
        boolean isDayVerified = true;
        boolean matchesFormat = dateInput.getText().matches("##/##/####".replaceAll("#", "[0-9]"));

        // Verify that month number is between 1-12
        if (dateInput.getText().substring(0, 2).matches("##".replaceAll("#", "[0-9]"))) {
            isMonthVerified = Integer.parseInt(dateInput.getText().substring(0, 2)) >= 1
                    && Integer.parseInt(dateInput.getText().substring(0, 2)) <= 12;

        }

        // Verify that day number is between 1 and 31
        if (dateInput.getText().substring(3, 5).matches("##".replaceAll("#", "[0-9]"))) {
            isDayVerified = Integer.parseInt(dateInput.getText().substring(3, 5)) >= 1
                    && Integer.parseInt(dateInput.getText().substring(3, 5)) <= 31;
        }

        return isMonthVerified && isDayVerified && matchesFormat;
    }
}
