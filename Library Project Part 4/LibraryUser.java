import java.util.*;
import java.io.*;
/**
 * A class that simulates of the library user
 * 
 * @author (Sabir Hussein) 
 * @version (22/03/24)
 */

public class LibraryUser {
    // Fields
    private String userID;
    // The id of the user
    private String surname;
    // The surname of the user
    private String firstName;
    // The first name of the user
    private String otherInitials;
    // The other initials of the user
    private String title;
    // Title of the user (Dr, Miss)
    
    // Getters 
    public String getUserID() {
        return userID;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOtherInitials() {
        return otherInitials;
    }

    public String getTitle() {
        return title;
    }
    
    // Setters 
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setOtherInitials(String otherInitials) {
        this.otherInitials = otherInitials;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // Method to print user details    
    public void printDetails() {
        System.out.println("User ID: " + userID);
        System.out.println("Name: " + title + " " + firstName + " " + otherInitials + " " + surname);
        System.out.println("");
    }

    // Method to read user data
    public void readData(Scanner scanner) {
        userID = scanner.next();
        surname = scanner.next();
        firstName = scanner.next();
        otherInitials = scanner.next();
        title = scanner.next();
    }
    
     // Method to write user data
    public void writeData(PrintWriter writer) {
        writer.println(userID + ", " + surname + ", " + firstName + ", " + otherInitials + ", " + title);
    }
}
