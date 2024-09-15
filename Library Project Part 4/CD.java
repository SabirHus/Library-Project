import java.util.*;
/**
 * A class that holds the CD data
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class CD extends AudioVisual {
    // Additional field for CDs
    private String artist;
    private int numberOfTracks;

    //Getters
    public String getArtist() {
        return artist;
    }
    
    public int getNumberOfTracks() {
        return numberOfTracks;
    }
    
    //Setters 
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    //Read Data
    @Override
    public void readData(Scanner scanner) {
        // Read field specific to CDs
        this.artist = scanner.next();
        this.numberOfTracks = scanner.nextInt();
        
        // Call readItemData method of superclass to read common fields
        super.readData(scanner);
    }
    
    //Print Details
    @Override
    public void printDetails() {
        // Print details specific to CDs
        System.out.println("CD Data:");
        System.out.println("Artist: " + artist);
        System.out.println("Number of Tracks: " + numberOfTracks);
        
        // Call printDetails method of superclass to print common details
        super.printDetails();
        System.out.println("");
    }
}
