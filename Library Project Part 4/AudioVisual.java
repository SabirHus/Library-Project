import java.util.*;
/**
 * This is a class that holds the Audio Visual data (Only the data same in CD and DVD)
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */
public class AudioVisual extends LibraryItem {
    // Additional field for audio-visual items
    private int playingTime;

    // Getter and setter methods for field
    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    //Read Data
    @Override
    public void readData(Scanner scanner) {
        // Read field specific to audio-visual items
        this.playingTime = scanner.nextInt();

        // Call readItemData method of superclass to read common fields
        super.readData(scanner);
    }
    
    //Print Details
    @Override
    public void printDetails() {
        // Print details specific to audio-visual items
        System.out.println("Playing Time: " + playingTime + " minutes");

        // Call printDetails method of superclass to print common details
        super.printDetails();
                
        System.out.println("");
    }
}