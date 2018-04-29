package Containers;

import java.util.ArrayList;

public class ItemDatabase {

    // INSTANCE VARIABLES-------------------------------
    private final ArrayList<Item> itemDatabase;

    // CONSTRUCTORS-------------------------------------
    public ItemDatabase() {
        itemDatabase = new ArrayList<>();
    }

    // ACCESSORS----------------------------------------
    public ArrayList getItemDatabase() {
        return itemDatabase;
    }

    public Item getItem(int index) {
        if (itemDatabase.get(index) != null) {
            return itemDatabase.get(index);
        } else {
            return null;
        }
    }

    // OTHER INTERFACE METHODS--------------------------
    public void addNewItem(int index, Item newItem) {
        itemDatabase.add(index, newItem);

        // Push Changes to Item Database Table
    }

    public void addNewItem(Item newItem) {
        itemDatabase.add(newItem);

        // Push Changes to Item Database Table
    }

    public void removeItem(int index) {
        itemDatabase.remove(index);

        // Push Changes to Item Database Table
    }
    
    // SUPPORT METHODS----------------------------------
    private void clearItemDatabase(){
        itemDatabase.clear();
    }

}
