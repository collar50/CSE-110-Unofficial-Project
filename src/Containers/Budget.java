package Containers;

import java.util.ArrayList;

public class Budget {

    // INSTANCE VARIABLES-------------------------------------------
    private ArrayList<BudgetItem> budget;
    private String name, description, date;

    // CONSTRUCTORS-------------------------------------------------
    public Budget(ItemDatabase itemDatabase, String initName, String initDescription, String initDate) {
        name = initName;
        description = initDescription;
        date = initDate;        
        
        for (Object i : itemDatabase.getItemDatabase()) {
            budget.add(new BudgetItem((Item) i));
        }

        // Create new table in database.
    }
    
    // ACCESSORS
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getDate(){
        return date;
    }

    // OTHER INTERFACE METHODS---------------------------------------------
    public void add(BudgetItem newBudgetItem){
        budget.add(newBudgetItem);
        // Do Database Stuff
    }
    
    
    public double getTotalCost() {
        double totalCost = 0;
        for (Object bi : budget) {
            BudgetItem budgetItem = (BudgetItem) bi;
            totalCost += budgetItem.getCost();
        }
        return totalCost;
    }

    public double getTotalRecurringCost() {
        double totalCost = 0;
        for (Object bi : budget) {
            BudgetItem budgetItem = (BudgetItem) bi;
            totalCost += budgetItem.getCost();
        }
        return totalCost;
    }

}
