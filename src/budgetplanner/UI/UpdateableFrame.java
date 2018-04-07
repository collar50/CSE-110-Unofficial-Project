package budgetplanner.UI;


import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UpdateableFrame extends JFrame{
    // Instance Variables
    private final JPanel container;
    private final Subpanel mainMenuPanel;
    private final Subpanel itemDatabasePanel;
    //private final Subpanel budgetList;
    //private Subpanel[] budgets = {};
    private final CardLayout card;
    
    
    
    // CONSTRUCTORS ------------------------------------------------------------
    public UpdateableFrame(){
        // Local Constants
        final int WIDTH = 500;
        final int HEIGHT = 750;
        
        // Instantiate our card layout, our container panel, and our subpanels.
        card = new CardLayout();
        container = new JPanel();
        mainMenuPanel = new MainMenuPanel(this);
        itemDatabasePanel = new ItemDatabasePanel(this);
        
        // Create components in subpanels
        //mainMenuPanel.CreatePanel(this);
        //itemDatabasePanel.CreatePanel(this);
        
        // Set the layout of the container and add subpanels.
        container.setLayout(card);
        container.add(mainMenuPanel, mainMenuPanel.getPanelName());
        container.add(itemDatabasePanel, itemDatabasePanel.getPanelName());
        
        // Show the main menu panel -> This is how we decide which subpanel
        // we see.
        card.show(container, mainMenuPanel.getPanelName());
        
        // Set up the frame.
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(container);
        this.setVisible(true);        
    }
    
    // ACCESSORS ---------------------------------------------------------------
    public Subpanel getMainMenuPanel(){
        return mainMenuPanel;
    }
    
    public Subpanel getItemDatabasePanel(){
        return itemDatabasePanel;
    }
    
    // OTHER INTERFACE METHODS -------------------------------------------------
    public void changeView(Subpanel newPanel){
        card.show(container, newPanel.getPanelName());
    }
    
    public void addBudgetPanel(Subpanel newBudgetPanel){
        
    }
}