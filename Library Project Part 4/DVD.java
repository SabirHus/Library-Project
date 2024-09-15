import java.util.*;
/**
 * A class that holds the DVD data
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class DVD extends AudioVisual {
    // Additional field for DVDs
    private String director;

    // Getter and setter methods for field
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    //Read Data
    @Override
    public void readData(Scanner scanner) {
        // Read field specific to DVDs
        this.director = scanner.next();

        // Call readItemData method of superclass to read common fields
        super.readData(scanner);
    }
    
    //Print Details
    @Override
    public void printDetails() {
        // Print details specific to DVDs
        System.out.println("DVD Data:");
        System.out.println("Director: " + director);

        // Call printDetails method of superclass to print common details
        super.printDetails();
        System.out.println("");
    }
}
