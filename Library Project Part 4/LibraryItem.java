import java.util.*;
/**
 * A class that simulates a library item from a library
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public abstract class LibraryItem {
    // Fields
    private String title;
    // The title of the item
    private String itemCode;
    // The code of the item
    private int cost;
    // The cost of the item
    private int timesBorrowed;
    // The times it has been borrowed
    private boolean onLoan;
    // If it is on loan or not
    
    //Getters
    public String getTitle() {
        return title;
    }
    
    public String getItemCode() {
        return itemCode;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getTimesBorrowed() {
        return timesBorrowed;
    }
    
    public boolean getOnLoan() {
        return onLoan;
    }
    
    //Setters 
    public void setTitle(String title) {
        this.title = title;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setTimesBorrowed(int timesBorrowed) {
        this.timesBorrowed = timesBorrowed;
    }

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }      

    // Abstract method to read item data
    public void readData(Scanner scanner)
    {
        this.title = scanner.next();
        this.itemCode = scanner.next();
        this.cost = scanner.nextInt();
        this.timesBorrowed = scanner.nextInt();
        this.onLoan = scanner.nextBoolean();
    }
    
    // Method to print details
    public void printDetails() {
        System.out.println(title + " with item code " + itemCode + " has been borrowed " + timesBorrowed + " times.");
        if (onLoan) {
            System.out.println("This item is at present on loan and when new cost " + cost + " pence.");
        } else {
            System.out.println("This item is not on loan and when new cost " + cost + " pence.");
        }
    }
}

