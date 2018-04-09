/*
    @TODO 
        1) Set up the 'manage budgets' subpanel.
        2) Hook b2 button up to 'manage budgets' panel. 
        3) Play with colors in the Subpanel file to make something better looking. 
 */
package budgetplanner.UI;

//---------SWING-----------
// Component stuff
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

// Lists
public class MainMenuPanel extends Subpanel {

    // CONSTRUCTORS ------------------------------------------------------------
    public MainMenuPanel(UpdateableFrame frame) {
        panelName = "mainMenu";

        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpButtons(frame);
    }

    // INTERFACE METHODS -------------------------------------------------------
    @Override
    public void CreatePanel(UpdateableFrame frame) {
        /*
            Instantiate components. Usually want
                Panel(s)
                Layouts for each Panel                
                Buttons, Labels, Etc.      
                Borders for components
         */

        // Panels / Layouts
        setUpGridBagConstraints();
        setUpPanel();
        setUpLabels();
        setUpButtons(frame);
    }

    // SUPPORT METHODS ---------------------------------------------------------
    private void setUpGridBagConstraints() {
        gbc = new GridBagConstraints(); // Used to modify properties of cells. 
        gbc.insets = new Insets(0, 0, 100, 0); // Sets spacing between cells. 
    }

    private void setUpPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(BACK_COLOR);
    }

    private void setUpLabels() {
        JLabel title;
        title = new JLabel("<html><center>Budget<br/>Planner", SwingConstants.CENTER);
        DecorateComponent(title, LABEL_COLOR, 50, LABEL_BORDER);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PositionComponent(gbc, title, 0, 0, 0, 0);
    }

    private void setUpButtons(UpdateableFrame frame) {
        /*
            Action listener class for button. This is what button does when 
            clicked.
         */
        class ItemDatabaseListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.changeView(frame.getItemDatabasePanel());
            }
        }

        JButton b1, b2;

        b1 = new JButton("Item Database");
        DecorateComponent(b1, BUTTON_COLOR, 25, BUTTON_BORDER);
        PositionComponent(gbc, b1, 0, 1, .5f, 0);
        ActionListener listener = new ItemDatabaseListener();
        b1.addActionListener(listener);

        b2 = new JButton("Manage Budgets");
        DecorateComponent(b2, BUTTON_COLOR, 25, BUTTON_BORDER);
        PositionComponent(gbc, b2, 0, 2, .5f, 1f); // need to make weightY of last component 1 to push stuff up to the top. 

    }
}
