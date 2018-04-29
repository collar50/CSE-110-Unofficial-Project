package Containers;

public class Item {

    // INSTANCE VARIABLES------------------------------------------------
    private String name, description;
    private double price;

    // CONSTRUCTORS------------------------------------------------------
    public Item(String initName, String initDescription, double initPrice) {
        name = initName;
        description = initDescription;
        price = initPrice;
    }

    public Item(String initName, String initDescription) {
        name = initName;
        description = initDescription;

        price = 0;
    }

    public Item(String initName, double initPrice) {
        name = initName;
        price = initPrice;

        description = "";
    }

    public Item(String initName) {
        name = initName;

        description = "";
        price = 0;
    }

    // ACCESSORS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    
    public String getPriceString(){
        return Double.toString(price);
    }
    
    // MUTATORS
    public void setName(String newName){
        name = newName;
    }
    
    public void setDescription(String newDescription){
        description = newDescription;
    }
    
    public void setPrice(double newPrice){
        price = newPrice;
    }
}
