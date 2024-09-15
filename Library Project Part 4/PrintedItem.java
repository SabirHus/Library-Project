import java.util.*;
/**
 * This is a class that holds the PrintedItem data (Only common to book and periodical)
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class PrintedItem extends LibraryItem {
    // Fields for printed items
    private int numberOfPages;
    private String publisher;

    // Getters
    public int getNumberOfPages() {
        return numberOfPages;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    //Setters 
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Read Data
    @Override
    public void readData(Scanner scanner) {
        // Read fields specific to printed items
        this.numberOfPages = scanner.nextInt();
        this.publisher = scanner.next();

        // Call readItemData method of superclass to read common fields
        super.readData(scanner);
    }
   
    //Print Details
    @Override
    public void printDetails() {
        // Print details specific to printed items
        System.out.println("Number of Pages: " + numberOfPages);
        System.out.println("Publisher: " + publisher);

        // Call printDetails method of superclass to print common details
        super.printDetails();
    }
}
