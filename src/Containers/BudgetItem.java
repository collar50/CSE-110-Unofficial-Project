package Containers;

public class BudgetItem {

    // INSTANCE VARIABLES
    private Item item;
    private int quantity, recurringQuantity;

    // CONSTRUCTORS
    public BudgetItem(Item initItem) {
        item = initItem;
        quantity = recurringQuantity = 0;
    }
    
    // ACCESSORS
    public int getQuantity(){
        return quantity;
    }
    
    public int getRecurringQuantity(){
        return recurringQuantity;
    }

    // MUTATORS
    public void setQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            quantity = newQuantity;

            recurringQuantity = newQuantity < recurringQuantity ? newQuantity : recurringQuantity;
        }
    }

    public void setRecurringQuantity(int newRecurringQuantity) {
        if (newRecurringQuantity >= 0) {
            recurringQuantity = newRecurringQuantity;
            
            quantity = newRecurringQuantity > quantity ? newRecurringQuantity : quantity;
        }
    }
    
    // OTHER INTERFACE METHODS
    public double getCost(){
        double cost = item.getPrice() * quantity;
        return cost;
    }
    
    public double getRecurringCost(){
        double recurringCost = item.getPrice() * recurringQuantity;
        return recurringQuantity;
    }

}
