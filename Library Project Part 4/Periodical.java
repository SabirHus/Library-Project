import java.util.*;
/**
 * This is a class that holds the periodical data
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */

public class Periodical extends PrintedItem {
    // Additional field for periodicals
    private String publicationDate;
    
     // Getter and setter methods for fields
    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    // Method to read periodical data
    @Override
    public void readData(Scanner scanner) {
        // Additional field for periodicals
        this.publicationDate = scanner.next();
        // Call readItemData method of superclass to read common fields
        
        // Read additional fields specific to books
        super.readData(scanner);
    }
    
    // Method to print details of a periodical
    @Override
    public void printDetails() {
            System.out.println("Periodical Data:");
            System.out.println("Publication Date: " + publicationDate);
            
             // Call printDetails method of superclass
            super.printDetails(); 
            
            // Add a gap in between each section
            System.out.println("");
        }
}
